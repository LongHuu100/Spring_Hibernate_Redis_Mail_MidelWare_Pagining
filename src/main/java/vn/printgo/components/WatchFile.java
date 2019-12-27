package vn.printgo.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import vn.printgo.crm.BeanUtil;

@Component
public class WatchFile  implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(WatchFile.class);
	private static Logger cdrFile = LoggerFactory.getLogger("cdrFileDesign");
	
	@Override
	public void run() {
		
		Environment ev = (Environment) BeanUtil.getBean(Environment.class);
		
		final Path path = FileSystems.getDefault().getPath(ev.getProperty("watch.folder"));
		
		try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
			path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		    while (true) {
		        final WatchKey wk = watchService.take();
		        for (WatchEvent<?> event : wk.pollEvents()) {
		        	Path directory = (Path) wk.watchable();
		        	Path fullPath = directory.resolve( (Path) event.context());
		            File file = fullPath.toFile();
		            cdrFile.info(file.getAbsolutePath());
		            if(!file.exists()) continue;
		            try {
		            	String[] array = ( file.getAbsolutePath( )).split("/");
		            	if(array.length > 2) {
		            		String danhmuc = array[array.length - 3];
			            	String dateCreated = array[array.length - 2];
			            	String fileName = array[array.length - 1];
			            	if(fileName.startsWith(".")) continue;
			            	logger.info("danhmuc: " + danhmuc + ", dateCreated: " + dateCreated + ", fileName: " + fileName);
			            }
		            } catch (Exception ex) {}
		        }
		        // reset the key
		        boolean valid = wk.reset();
		        if (!valid) {
		        	logger.info("Key has been unregisterede");
		        }
		    }
		} catch (IOException | InterruptedException e) {
			logger.error(e.getMessage());
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e1) {}
		}
	}
}

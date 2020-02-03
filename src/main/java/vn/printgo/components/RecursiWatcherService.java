package vn.printgo.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import vn.printgo.model.TmpFile;
import vn.printgo.service.TmpFileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

@Service
public class RecursiWatcherService {

    private static final Logger LOG = LoggerFactory.getLogger(RecursiWatcherService.class);
    private static Logger cdrFile = LoggerFactory.getLogger("cdrFileDesign");
    
    @Value("${watch.folder}")
    private File rootFolder;
    
    @Autowired
    TmpFileService tmpFileService;

    private WatchService watcher;
    private ExecutorService executor;

    @PostConstruct
    public void init() throws IOException {
        watcher = FileSystems.getDefault().newWatchService();
        executor = Executors.newSingleThreadExecutor();
        startRecursiveWatcher();
    }

    private String md5File(String fN) {
    	return DigestUtils.md5DigestAsHex(fN.getBytes());
    }
  
    @SuppressWarnings("unchecked")
	private void startRecursiveWatcher() throws IOException {
        LOG.info("Starting Recursive Watcher");

        final Map<WatchKey, Path> keys = new HashMap<>();

        Consumer<Path> register = p -> {
            if (!p.toFile().exists() || !p.toFile().isDirectory()) {
                throw new RuntimeException("folder " + p + " does not exist or is not a directory");
            }
            try {
                Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        LOG.info("registering " + dir + " in watcher service");
                        WatchKey watchKey = dir.register(watcher, new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE});
                        keys.put(watchKey, dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
            	LOG.error("Error registering path " + p);
            }
        };

        register.accept(rootFolder.toPath());

        executor.submit(() -> {
            while (true) {
                final WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException ex) {
                    return;
                }

                final Path dir = keys.get(key);
                if (dir == null) {
                    System.err.println("WatchKey " + key + " not recognized!");
                    continue;
                }

                key.pollEvents().stream()
                .filter(e -> (e.kind() != OVERFLOW))
                .map(e -> ((WatchEvent<Path>) e).context())
                .forEach(p -> {
                    final Path absPath = dir.resolve(p);
                    if (absPath.toFile().isDirectory()) {
                        register.accept(absPath);
                    } else {
                        final File f = absPath.toFile();
                        LOG.info("absPath: " + f.getAbsolutePath() );
                        saveFile(f);
                    }
                });

                boolean valid = key.reset();
                if (!valid) {
                	LOG.error("Error key.reset " + valid);
                }
            }
        });
    }
    
    private void saveFile(File f) {
    	LOG.info("absPath: " + f.getAbsolutePath() );
    	String[] array = ( f.getAbsolutePath( )).split("/");
		if(array.length <= 2) return;
		String danhmuc = array[array.length - 3];
		String dateCreated = array[array.length - 2];
		String fileName = array[array.length - 1];
		if(!fileName.startsWith(".") && f.isFile() && !fileName.contains("filepart")) {
			cdrFile.info(f.getAbsolutePath());
			LOG.info("danhmuc: " + danhmuc + ", dateCreated: " + dateCreated + ", fileName: " + fileName);
			TmpFile tmpFile = tmpFileService.findByName(fileName);
			if(tmpFile == null) {
				TmpFile _tmpFile = new TmpFile();
				_tmpFile.setName(fileName);
				_tmpFile.setPath(f.getAbsolutePath());
				_tmpFile.setNameMd(md5File(fileName));
				tmpFileService.save(_tmpFile);
			}
		} else if (!fileName.contains("filepart")) {
			LOG.info("delete danhmuc: " + danhmuc + ", dateCreated: " + dateCreated + ", fileName: " + fileName);
			tmpFileService.deleteByName(fileName);
		}
    }
}
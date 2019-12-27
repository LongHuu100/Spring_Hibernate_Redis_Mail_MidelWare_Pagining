package vn.printgo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.printgo.components.MediaTypeUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/static-file")
public class StaticFileController {
	
	private Logger logger = LoggerFactory.getLogger(StaticFileController.class);
	
    @Autowired
    private ServletContext servletContext;
    
    @Autowired
	private Environment environment;
    
	@GetMapping(value = {"/", ""})
    public void SendFile(
    		HttpServletResponse resonse,
    		@RequestParam(value="file-name") String fileName) throws IOException {
		
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        
        File file = new File(environment.getProperty("amazon.static.file") + "/" + fileName);
        logger.info("file path: {}", file.getAbsolutePath());
        if(!file.isFile()) return;
        
        // Content-Type
        // application/pdf
        resonse.setContentType(mediaType.getType());
 
        // Content-Disposition
        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());
 
        // Content-Length
        resonse.setContentLength((int) file.length());
 
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
 
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }
}

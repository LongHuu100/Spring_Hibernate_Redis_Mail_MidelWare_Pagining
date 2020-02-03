package vn.printgo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.printgo.components.MediaTypeUtils;
import vn.printgo.entities.ErrorData;
import vn.printgo.entities.RList;
import vn.printgo.entities.RTmpFile;
import vn.printgo.model.TmpFile;
import vn.printgo.service.TmpFileService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/static-file")
public class StaticFileController {
	
	private Logger logger = LoggerFactory.getLogger(StaticFileController.class);
	
    @Autowired
    private ServletContext servletContext;
    
    @Autowired
	private TmpFileService tmpFileService;
    
	@GetMapping(value = {"/", ""})
    public void SendFile(
    		HttpServletResponse resonse,
    		@RequestParam(value="file-name") String fileName) throws IOException {
		
		TmpFile find = tmpFileService.findLikeNameMd5(fileName);
		if(find == null) return;
		
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        
        // File file = new File(environment.getProperty("amazon.static.file") + "/" + fileName);
		logger.info("file path: {}", find.getPath() );
		File file = new File( find.getPath() );
        if(!file.isFile()) return;
        
        // Content-Type
        resonse.setContentType(mediaType.getType());
        // Content-Disposition
        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());
        // Content-Length
        resonse.setContentLength((int) file.length());
 
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
 
        byte[] buffer = new byte[2048];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }
	
	@GetMapping(value = {"/mode-check"})
	@ResponseBody
	public String modeCheck(@RequestParam(value="file-name") String fileName) {
		TmpFile chekF = tmpFileService.findLikeNameMd5(fileName);
		logger.info("chekF: {}", chekF);
		return chekF != null ? "OK" : "SR";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = {"/get-by-name"})
	@ResponseBody
    public ResponseEntity<?> GetByName(
    		HttpServletResponse resonse,
    		@RequestParam(value="file-name") String fileName) {
		List<RTmpFile> tmpFile = tmpFileService.findLikeName(fileName);

		RList<RTmpFile> _response = new RList<RTmpFile>(
            ErrorData.SUCCESS, 
            ErrorData.getMessage(ErrorData.SUCCESS)
		);
		
		_response.setData(tmpFile);
        
        return new ResponseEntity(_response, HttpStatus.OK);
	}
	
	@GetMapping(value = {"/preview"})
	public ResponseEntity<byte[]> viewOnBrowser(
		@RequestParam(value="file-name") String fileName) throws IOException {
		
		TmpFile find = tmpFileService.findLikeNameMd5(fileName);
		if(find == null) return null;
		
        File file = new File( find.getPath());
        if(!file.isFile()) return null;
        
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        final HttpHeaders headers = new HttpHeaders();
       
	    headers.setContentType(mediaType.getType().equals("application") ? MediaType.APPLICATION_PDF : MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(
    		Files.readAllBytes(file.toPath()), 
    		headers, 
    		HttpStatus.CREATED
	    );
	}
}

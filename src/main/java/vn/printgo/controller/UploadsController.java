package vn.printgo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.printgo.components.AmazonUtil;
import vn.printgo.entities.ErrorData;
import vn.printgo.entities.RObject;
import vn.printgo.model.Product;
import vn.printgo.service.ProductServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/uploads")
public class UploadsController {
	
	Logger logger = LoggerFactory.getLogger(UploadsController.class);
	
	@Autowired
	private AmazonUtil amazonUtil;

	private FileOutputStream fileOutputStream;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity<?> product(
    		@RequestParam(value="file-name") String fileName,
    		@RequestParam(value="file-content") String fileContent) {
		
		RObject<String> _response = new RObject<String>(
            ErrorData.SUCCESS, 
            ErrorData.getMessage(ErrorData.SUCCESS)
        );
		String directory= ProductServiceImpl.dirUpload + amazonUtil.getFolderUpload(AmazonUtil.dateToInt());
		
		File f = new File(directory);
		if(!f.exists()) f.mkdirs();
		
		try {
			
            byte[] imageByte = Base64.getDecoder().decode(fileContent);
            String _fileName = directory + "/" + Product.setFileName(fileName) + ".png";
            fileOutputStream = new FileOutputStream(_fileName);
			fileOutputStream.write(imageByte);
            
            _response.setMessage("success");
        } catch (Exception ex) {
        	logger.error("uploadImage ex: {}", ex.getMessage() );
        	_response.setErrorCode(ErrorData.UPLOAD_FILE_FAIL);
        	_response.setMessage(ErrorData.getMessage(_response.getErrorCode()));
        }
		
        return new ResponseEntity(_response, HttpStatus.OK);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/rsync", method = RequestMethod.POST)
	public ResponseEntity<?> UpFile(@RequestBody RsyncFile rsyncFile) {
		logger.info("danhmuc: {}, dateCreated: {}", rsyncFile.danhmuc, rsyncFile.dateCreated);
		logger.info("maDon: {}", rsyncFile.maDon);
		return new ResponseEntity("OK", HttpStatus.OK);
	}
	
	public static class RsyncFile {
		public RsyncFile() {}
		public String danhmuc;
		public String dateCreated;
		public String maDon;
	}
}

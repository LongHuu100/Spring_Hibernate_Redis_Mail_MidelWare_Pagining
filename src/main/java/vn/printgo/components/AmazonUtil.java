package vn.printgo.components;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AmazonUtil {

	private static Logger logger = LoggerFactory.getLogger(AmazonUtil.class);
	private static final int VALUE_NOT_CONVERT = 0;
	
	@Autowired
	private Environment environment;
	
	public String getFileById(int objectId, String imageFile, String folder) {
		
		if(objectId == VALUE_NOT_CONVERT) 
			return null;
		
		String patter = "^(http|https|ftp)://.*$";
        if (imageFile.matches(patter)){
            return imageFile;
        } else {
        	int total = (int) Math.ceil((double)objectId/2048);
            String filePath = '/' + folder + '/' + total + '/' + imageFile;
            return environment.getProperty("amazon.cdn") + filePath;
        }
	}
	
	public int getFolderUpload(Integer objectId) {

		String _dateStr = new SimpleDateFormat("MM-01-yyyy")
                .format(new Date(objectId * 1000L));
		Date _date;
		try {
			_date = new SimpleDateFormat("MM-01-yyyy").parse(_dateStr);
		} catch (ParseException e) {
			logger.info("ParseException Date : {}", e.getMessage());
			return VALUE_NOT_CONVERT;
		}
		
		return (int) Math.ceil((double) AmazonUtil.dateToInt(_date)/2048);
	}
	
	public static int dateToInt() {
		return (int) (new Date().getTime()/1000);
	}
	
	public static int dateToInt(Date _d) {
		return (int) (_d.getTime()/1000);
	}
}

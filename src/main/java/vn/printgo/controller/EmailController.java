package vn.printgo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import freemarker.template.Template;

import vn.printgo.entities.ErrorData;
import vn.printgo.entities.RList;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class EmailController {
	
	Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
    private JavaMailSender sender;
 
	@Autowired
    private Configuration freemarkerConfig;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = {"/send"})
    @PreAuthorize("hasRole('DEVICE') or hasRole('USER')")
    public ResponseEntity<?> timeInClass(){
        RList<String> responseClient = new RList<String>(
            ErrorData.SUCCESS, 
            ErrorData.getMessage(ErrorData.SUCCESS)
        );
        try {
            sendEmail();
        }catch(Exception ex) {
        	logger.error("Error in sending email: {}", ex );
        }
        return new ResponseEntity(responseClient, HttpStatus.OK);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void sendEmail() throws Exception {
		
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
 
        Map<String, Object> model = new HashMap();
        model.put("user", "qpt");
        
        // set loading location to src/main/resources
        // You may want to use a subfolder such as /templates here
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
        
        Template t = freemarkerConfig.getTemplate("welcome.ftl");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
 
        helper.setTo("long.huu.100@gmail.com");
        helper.setText(text, true); // set to html
        helper.setSubject("Thông báo đơn hàng NC26091905");
 
        sender.send(message);
    }
}

package vn.printgo.controller;

import vn.printgo.components.JwtGenericException;
import vn.printgo.entities.ErrorData;
import vn.printgo.entities.RList;
import vn.printgo.entities.RObject;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import javax.servlet.ServletException;

@ControllerAdvice // Exception cho tat ca cac controller text html
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseEntity<ErrorData> handleErrorHandler(Exception e) {
        RList _rList = new RList(1, e.getMessage());
        logger.error("handleErrorHandler --> {}", e);
        return new ResponseEntity(_rList, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseBody
    public ResponseEntity<ErrorData> handleBusinessException(AccessDeniedException e) {
        RList _rList = new RList(403, e.getMessage());
        logger.error("handleBusinessException --> ", e.getMessage());
        return new ResponseEntity(_rList, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ExceptionHandler(value = {JwtGenericException.class})
    @ResponseBody
    public ResponseEntity<ErrorData> handleJwtGenericException(JwtGenericException e) {
        RList _rList = new RList(e.getErrCode(), e.getMessage());
        return new ResponseEntity(_rList, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
    
    // error handle for @Valid
    // Bắt lỗi kiểm tra dữ liệu đầu vào của các model.
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseBody
    protected ResponseEntity<ErrorData> handleMethodArgumentNotValid(ConstraintViolationException ex) {
        RObject _rObj = new RObject(1, "Lỗi dữ liệu đầu vào");
        Set<? extends ConstraintViolation<?>> _constraintViolations =  ex.getConstraintViolations();
        String _data = _constraintViolations.stream()
    			.map( cv -> cv == null ? "null" : cv.getMessage() )
    			.collect( Collectors.joining( ", " ) );
        //Get all errors
        _rObj.setData(_data);
        return new ResponseEntity(_rObj, HttpStatus.BAD_REQUEST);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(value = {
		NoHandlerFoundException.class,
		ServletException.class
	})
    @ResponseBody
    public ResponseEntity<ErrorData> handleErrorRunTime(NoHandlerFoundException e) {
        RList _rList = new RList(404, e.getMessage());
        logger.error("handleErrorRunTime --> {}", e);
        return new ResponseEntity(_rList, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

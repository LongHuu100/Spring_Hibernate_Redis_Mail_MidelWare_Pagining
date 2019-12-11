package vn.printgo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.printgo.entities.ErrorData;
import vn.printgo.entities.RObject;
import vn.printgo.pagination.Ipage;
import vn.printgo.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/get-product", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(
    		@RequestParam(value="page") Integer page,
    		@RequestParam(value="category-id") Integer categoryId){
		
		logger.info("categoryId Id: {}", categoryId );
		Ipage _rPro = categoryService.getListProduct(categoryId, page);
		RObject _response = new RObject(
            ErrorData.SUCCESS, 
            ErrorData.getMessage(ErrorData.SUCCESS)
        );
		
        _response.setData(_rPro);
        
        return new ResponseEntity(_response, HttpStatus.OK);
    }
}

package vn.printgo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.printgo.components.AmazonUtil;
import vn.printgo.entities.ErrorData;
import vn.printgo.entities.RObject;
import vn.printgo.entities.RProduct;
import vn.printgo.model.Product;
import vn.printgo.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AmazonUtil amazonUtil;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PreAuthorize("hasRole('DEVICE') or hasRole('USER')")
	@RequestMapping(value = "/get-product", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(
    		@RequestParam(value="id") Integer id){
		
		RProduct pro = productService.findById(id);
		RObject<String> _response = new RObject<String>(
            ErrorData.SUCCESS, 
            ErrorData.getMessage(ErrorData.SUCCESS)
        );
        _response.setData(pro);
        
        return new ResponseEntity(_response, HttpStatus.OK);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DEVICE') or hasRole('USER')")
    public ResponseEntity<?> addProduct(
    		@RequestParam(value="name") String name){
		
		Product pro = new Product();
		productService.saveProduct(pro);
		RObject<String> _response = new RObject<String>(
            ErrorData.SUCCESS, 
            ErrorData.getMessage(ErrorData.SUCCESS)
        );
        _response.setData(pro.getImage());
        
        return new ResponseEntity(_response, HttpStatus.OK);
    }
}

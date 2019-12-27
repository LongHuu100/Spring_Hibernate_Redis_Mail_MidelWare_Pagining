package vn.printgo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.printgo.components.AmazonUtil;
import vn.printgo.dao.ProductDao;
import vn.printgo.entities.RProduct;
import vn.printgo.model.Product;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductDao prodao;
	
	@Autowired
	AmazonUtil amazonUtil;
	
	@Autowired
	private Environment environment;
	
	private static String UPLOAD_PATH =  "uploads/products/";
	public static String dirUpload= System.getProperty("user.dir") +  UPLOAD_PATH;
	
	@Override
	public RProduct findById(int id) {
		
		Product _prod = prodao.findById(id);
		logger.info("_prod: {}", _prod);
		if(_prod == null) return null;
		
		RProduct _resProd = new RProduct(
			_prod.getId(),
			_prod.getTypeId(),
			_prod.getProviderCode(),
			this.imageUrl(_prod)
		);
		return _resProd;
	}

	@Override
	public void saveProduct(Product product) {
		prodao.save(product);
	}

	@Override
	public void updateProduct(Product product) {}
	
	public String imageUrl(Product _prod) {
		Integer pathFile = amazonUtil.getFolderUpload(_prod.getCreatedAt());
		return environment.getProperty("amazon.cdn") + "/" + UPLOAD_PATH + pathFile + "/" + _prod.getImage();
	}
}

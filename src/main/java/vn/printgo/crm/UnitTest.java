package vn.printgo.crm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import vn.printgo.entities.RObject;
import vn.printgo.entities.RProduct;
import vn.printgo.redis.IRedisPublisher;
import vn.printgo.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=CrmApplication.class)
public class UnitTest {
	
	Logger logger = LoggerFactory.getLogger(UnitTest.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	IRedisPublisher redisPublisher;
	
	@Test
	public void getProductPractice() {
		RProduct pro = productService.findById(100);
		logger.info("productService: {}", pro);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void redisPublicser() {
		RObject rP = new RObject(200, "redisPublicser");
		rP.setData("Success");
		redisPublisher.publish(rP.toJson());
	}
}

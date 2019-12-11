package vn.printgo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.printgo.dao.CategoryDao;
import vn.printgo.dao.CategoryDaoImpl;
import vn.printgo.entities.RProduct;
import vn.printgo.model.Product;
import vn.printgo.pagination.Ipage;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private final int LIMIT = 1;
	@Autowired
	CategoryDao categoryDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Ipage getListProduct(int categoryId, int curentPage) {
		
		int totalItems = categoryDao.countItems(CategoryDaoImpl.QUERY_COUNT_PRODUCT, categoryId);
		List<Product> listProds = categoryDao.findProducts(categoryId, LIMIT, curentPage * LIMIT);
		
		List<RProduct>  result = listProds.stream().map(product -> {
			RProduct _rPo = new RProduct();
			_rPo.setId(product.getId());
			_rPo.setTypeId(product.getTypeId());
			_rPo.setProviderCode(product.getProviderCode());
            return _rPo;
        }).collect(Collectors.toList());
		
		Ipage _ipage = new Ipage(
			LIMIT, 
			totalItems, 
			curentPage, 
			result
		);
		return _ipage;
	}
}

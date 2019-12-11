package vn.printgo.dao;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.printgo.model.Category;
import vn.printgo.model.Product;

@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDao<Integer, Category> implements CategoryDao {

	Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
	public static final String QUERY_COUNT_PRODUCT = "countProduct";
	
	@Override
	public Category findById(int id) {
		return (Category) getByKey(id);
	}

	@Override
	public void save(Category category) {
		persist(category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProducts(int id, int limit, int offset) {

		List<Product> listProducts = getSession()
			.createNamedQuery("getProductById")
            .setParameter("cateId", id)
            .setParameter("pLimit", limit)
            .setParameter("pOffset", offset)
            .getResultList();
		logger.info("listProducts: {}", listProducts.size());
		return listProducts;
	}
	
	@Override
	public int countItems(String qCountName, int categoryId) {
		Object _vl = getSession().createNamedQuery( qCountName)
				.setParameter("cateId", categoryId)
				.getSingleResult();
		logger.info("countItems qCountName: {}, _vl: {}", qCountName, _vl);
		BigInteger bIn = (BigInteger) _vl;
		
		return bIn.intValue();
	}
}

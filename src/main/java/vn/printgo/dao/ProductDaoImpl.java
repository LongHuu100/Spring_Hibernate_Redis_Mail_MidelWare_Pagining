package vn.printgo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.printgo.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

	@Override
	public Product findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(Product product) {
		persist(product);
	}

	@Override
	public List<Product> findProduct(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}

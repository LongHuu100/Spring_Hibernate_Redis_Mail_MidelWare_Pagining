package vn.printgo.dao;

import java.util.List;

import vn.printgo.model.Product;

public interface ProductDao {
	Product findById(int id);
	void save(Product product);
	List<Product> findProduct(int offset, int limit);
}

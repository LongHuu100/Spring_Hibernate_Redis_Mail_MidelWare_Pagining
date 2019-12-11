package vn.printgo.dao;

import java.util.List;

import vn.printgo.model.Category;
import vn.printgo.model.Product;

public interface CategoryDao {
	Category findById(int id);
	void save(Category category);
	List<Product> findProducts(int id, int limit, int offset);
	int countItems(String qName, int id);
}

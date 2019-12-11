package vn.printgo.service;

import vn.printgo.pagination.Ipage;

public interface CategoryService {
	@SuppressWarnings("rawtypes")
	public Ipage getListProduct(int categoryId, int curentPage);
}

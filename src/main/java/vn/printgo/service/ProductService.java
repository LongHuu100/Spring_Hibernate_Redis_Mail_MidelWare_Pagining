/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.printgo.service;

import vn.printgo.entities.RProduct;
import vn.printgo.model.Product;

public interface ProductService {
	RProduct findById(int id);
    void saveProduct(Product product);
    void updateProduct(Product product);
}
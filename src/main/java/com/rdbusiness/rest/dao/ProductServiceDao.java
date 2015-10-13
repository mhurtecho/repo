package com.rdbusiness.rest.dao;

import com.rdbusiness.rest.bean.Product;

public class ProductServiceDao {

	ProductDao productDao;

	public ProductServiceDao() {
		productDao = new ProductDao();
	}

	public String getProductList() {
		return productDao.getList();
	}

	public String getProduct(String id) {
		return productDao.get(id);
	}

	public void updateProduct(String id, Product product) {
		productDao.update(id, product);
	}

	public void deleteProduct(String id) {
		productDao.delete(id);
	}

	public String createProduct(Product product) {
		return productDao.create(product);
	}
}

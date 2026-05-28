package se.jensen.linus.awsstoreproject.service;

import se.jensen.linus.awsstoreproject.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Integer id);

}

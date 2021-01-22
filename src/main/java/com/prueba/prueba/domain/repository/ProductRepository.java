package com.prueba.prueba.domain.repository;

import com.prueba.prueba.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScaredProduct(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);

}

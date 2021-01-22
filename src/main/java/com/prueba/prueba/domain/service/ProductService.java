package com.prueba.prueba.domain.service;

import com.prueba.prueba.domain.Product;
import com.prueba.prueba.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }
    
    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getScaredProduct(categoryId);
    }
    
    
    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
    public boolean delete(int productId){
        return getProduct(productId).map(prod -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

}

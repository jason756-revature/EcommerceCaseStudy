package com.ecommerce.service;

import com.ecommerce.dtos.ProductInfo;
import com.ecommerce.model.Product;
import com.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAllActive();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findActiveById(id);
    }

    public List<Product> findByKeyword(String keyword){
        return productRepository.findByKeyword("%" + keyword + "%");
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveAll(List<Product> productList, List<ProductInfo> metadata) {
        return productRepository.saveAll(productList);
    }

}

package com.ecommerce.controller;


import com.ecommerce.dtos.ProductInfo;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Optional<Product> optional = productService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> getProductByKeyword(@PathVariable String keyword){
        return ResponseEntity.ok(productService.findByKeyword(keyword));
    }


    @PutMapping
    public ResponseEntity<Product> upsert(@RequestBody Product product) {
        if (product.getId() == 0)
            product.setId(null);

        return ResponseEntity.ok(productService.save(product));
    }

    @PatchMapping
    public ResponseEntity<List<Product>> purchase(@RequestBody List<ProductInfo> metadata) {
        List<Product> productList = new ArrayList<Product>();

        for (int i = 0; i < metadata.size(); i++) {
            Optional<Product> optional = productService.findById(metadata.get(i).getId());

            if(!optional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Product product = optional.get();

            if(product.getQuantity() - metadata.get(i).getQuantity() < 0) {
                return ResponseEntity.badRequest().build();
            }

            product.setQuantity(product.getQuantity() - metadata.get(i).getQuantity());
            productList.add(product);
        }

        productService.saveAll(productList, metadata);

        return ResponseEntity.ok(productList);
    }


}

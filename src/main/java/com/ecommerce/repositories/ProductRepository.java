package com.ecommerce.repositories;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("FROM Product")
    List<Product> findAllActive();

    @Query("FROM Product WHERE id = :id")
    Optional<Product> findActiveById(int id);

    @Query("FROM Product WHERE (lower(name) LIKE lower(:keyword) OR lower(description) LIKE lower(:keyword))")
    List<Product> findByKeyword(String keyword);


}

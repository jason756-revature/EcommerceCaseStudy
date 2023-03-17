package com.ecommerce.repositories;

import com.ecommerce.model.Orders;
import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByUserId(User userId);
}

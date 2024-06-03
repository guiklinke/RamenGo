package com.RamenGo.RamenGo.repositories;

import com.RamenGo.RamenGo.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    boolean existsByDescription(String description);
}

package com.leksilab.leksipizzeria.persistence.repository;

import com.leksilab.leksipizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> { }

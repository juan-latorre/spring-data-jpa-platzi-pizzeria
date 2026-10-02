package com.platzi.pizza.persistence.repositoy;

import com.platzi.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
}

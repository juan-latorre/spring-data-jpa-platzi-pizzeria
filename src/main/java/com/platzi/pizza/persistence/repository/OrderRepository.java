package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.CustomerOrderDetail;
import com.platzi.pizza.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);
    @Query(value = """
        SELECT po.id_order AS idOrder,
               po.date AS orderDate,
               po.total AS total,
               c.name AS customerName,
               p.name AS pizzaName,
               p.price AS pizzaPrice
        FROM pizza_order po
        INNER JOIN customer c ON po.id_customer = c.id_customer
        INNER JOIN order_item oi ON po.id_order = oi.id_order
        INNER JOIN pizza p ON oi.id_pizza = p.id_pizza
        WHERE po.id_customer = :idCustomer
        ORDER BY po.date DESC, po.id_order DESC
        """, nativeQuery = true)
    List<CustomerOrderDetail> findCustomerOrders(@Param("idCustomer") String idCustomer);
    @Query(value =
            "SELECT po.id_order AS idOrder, " +
                    "       c.name AS customerName, " +
                    "       po.date AS orderDate, " +
                    "       po.total AS orderTotal, " +
                    "       GROUP_CONCAT(p.name SEPARATOR ', ') AS pizzaNames " +
                    "FROM pizza_order po " +
                    "INNER JOIN customer c ON po.id_customer = c.id_customer " +
                    "INNER JOIN order_item oi ON po.id_order = oi.id_order " +
                    "INNER JOIN pizza p ON oi.id_pizza = p.id_pizza " +
                    "WHERE po.id_order = :orderId " +
                    "GROUP BY po.id_order, c.name, po.date, po.total",
            nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int orderId);

}

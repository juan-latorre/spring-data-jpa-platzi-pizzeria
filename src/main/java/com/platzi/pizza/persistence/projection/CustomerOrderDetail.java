package com.platzi.pizza.persistence.projection;

import java.time.LocalDateTime;

public interface CustomerOrderDetail {
    Integer getIdOrder();
    LocalDateTime getOrderDate();
    Double getTotal();
    String getCustomerName();
    String getPizzaName();
    Double getPizzaPrice();
}

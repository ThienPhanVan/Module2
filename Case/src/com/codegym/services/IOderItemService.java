package com.codegym.services;


import com.codegym.model.OrderItem;
import java.util.List;

public interface IOderItemService {
    List<OrderItem> getOrderItems();

    void add(OrderItem newOrderItem);

    void update(OrderItem newOrderItem);

    OrderItem getOrderItemById(int id);

}

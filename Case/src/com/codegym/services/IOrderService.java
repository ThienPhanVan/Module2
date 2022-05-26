package com.codegym.services;
import com.codegym.model.Order;
import java.util.List;

public interface IOrderService {
    List<Order> getOrders();

    void add(Order newOrder);

    void update();

    Order getOrderById(int id);

    boolean exist(int id);


    void remove(Order order);
}

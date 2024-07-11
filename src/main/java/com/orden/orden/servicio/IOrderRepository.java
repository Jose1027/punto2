package com.orden.orden.servicio;

import com.orden.orden.modelo.Order;

import java.util.Map;

public interface IOrderRepository {
    void save(Order order);
    Order findById(Long id);
    Map<Long, Order> findAll();
}

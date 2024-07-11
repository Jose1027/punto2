package com.orden.orden.servicio;

import com.orden.orden.modelo.Item;
import com.orden.orden.modelo.Order;

public class OrderService {

    private final IOrderRepository iOrderRepository;
    private final IItemRepository iItemRepository;

    public OrderService(IOrderRepository iOrderRepository, IItemRepository iItemRepository) {
        this.iOrderRepository = iOrderRepository;
        this.iItemRepository = iItemRepository;
    }

    public void placeOrder(Order order) {
        validateOrder(order);
        checkInventory(order);
        iOrderRepository.save(order);
    }

    private void validateOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("La orden no es válida");
        }
        if (iOrderRepository.findById(order.getId()) != null) {
            throw new IllegalStateException("La orden con ID: " + order.getId() + " ya existe");
        }
    }

    private void checkInventory(Order order) {
        for (Item item : order.getItems()) {
            int stock = iItemRepository.getStock(item.getItemId());
            if (stock < item.getQuantity()) {
                throw new IllegalStateException("No hay suficiente inventario para el producto con ID: " + item.getItemId());
            }
        }
    }
}

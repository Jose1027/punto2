package com.orden.orden.databuilder;

import com.orden.orden.modelo.Item;
import com.orden.orden.modelo.Order;

import java.util.List;

public class OrderTestDataBuilder {
    private Long id;
    private String nombre;
    private List<Item> items;

    public OrderTestDataBuilder() {
        this.id = 1L;
        this.nombre = "Orden 1";
        this.items = List.of(new ItemTestDataBuilder().build());
    }

    public Order build() {
        return new Order(id, nombre, items);
    }

    public OrderTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public OrderTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public OrderTestDataBuilder conItems(List<Item> items) {
        this.items = items;
        return this;
    }
}

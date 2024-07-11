package com.orden.orden.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private Long id;
    private String nombre;
    private List<Item> items;
}

package com.orden.orden.databuilder;

import com.orden.orden.modelo.Item;

public class ItemTestDataBuilder {
    private Long itemId;
    private int quantity;

    public ItemTestDataBuilder() {
        this.itemId = 1L;
        this.quantity = 1;
    }

    public Item build() {
        return new Item(itemId, quantity);
    }

    public ItemTestDataBuilder conItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemTestDataBuilder conQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}

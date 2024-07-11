package com.orden.orden.servicio;

import com.orden.orden.databuilder.ItemTestDataBuilder;
import com.orden.orden.databuilder.OrderTestDataBuilder;
import com.orden.orden.modelo.Item;
import com.orden.orden.modelo.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private IOrderRepository iOrderRepository;

    @Mock
    private IItemRepository iItemRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        doNothing().when(iOrderRepository).save(any(Order.class));

        when(iItemRepository.getStock(1L)).thenReturn(5);
        when(iItemRepository.getStock(2L)).thenReturn(10);
    }

    @Test
    void testPlaceOrder() {
        Order order = new OrderTestDataBuilder().build();

        Item item1 = new ItemTestDataBuilder().conItemId(1L).conQuantity(2).build();
        Item item2 = new ItemTestDataBuilder().conItemId(2L).conQuantity(2).build();

        order.setItems(Arrays.asList(item1, item2));

        orderService.placeOrder(order);

        verify(iOrderRepository, times(1)).save(order);

        verify(iItemRepository, times(1)).getStock(1L);

        verify(iItemRepository, times(1)).getStock(2L);
    }

    @Test
    void testPlaceOrderInsufficientInventory() {
        Order order = new OrderTestDataBuilder().build();

        Item item1 = new ItemTestDataBuilder().conItemId(1L).conQuantity(10).build();


        order.setItems(Arrays.asList(item1));

        assertThrows(IllegalStateException.class, () -> orderService.placeOrder(order));

        verify(iOrderRepository, never()).save(order);

        verify(iItemRepository, times(1)).getStock(1L);

    }

}
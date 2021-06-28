package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.Order;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class  OrderServiceTest {

    @Autowired
    OrderService orderService;

    Order orderSuccess;
    Order orderFail;

    @BeforeEach
    public void orderInit() {
        orderSuccess = new Order();
        orderSuccess.setOrderId(1L);
        orderSuccess.setState("Ordered");

        orderFail = new Order();
        orderFail.setOrderId(null);
        orderFail.setState("");
    }

    @Test
    public void testOrderMake_success() {
        Order order = new Order();

        try {
            order = orderService.makeOrder(orderSuccess);
        } catch (Exception e) {
            //donothing
        }


        Assertions.assertEquals(1L, order.getOrderId());
        Assertions.assertEquals("Ordered", order.getState());
    }

    @Test
    public void testOrderValidation_success() {
        Order order = new Order();
        try {
            order = orderService.makeOrder(orderSuccess);
        } catch (Exception e) {
            fail("Should not throw exception");
        }

        Assertions.assertEquals(1L, order.getOrderId());
    }

    @Test
    public void testOrderValidation_fail() {
        Exception e = Assertions.assertThrows(Exception.class, () -> {
            orderService.makeOrder(orderFail);
        });

        Assertions.assertEquals("Order Validation Failed.", e.getMessage());
    }
}

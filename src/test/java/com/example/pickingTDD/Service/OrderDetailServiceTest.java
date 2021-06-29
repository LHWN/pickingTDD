package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.Order;
import com.example.pickingTDD.Entity.OrderDetail;
import com.example.pickingTDD.Entity.Sku;
import jdk.jshell.spi.ExecutionControlProvider;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class OrderDetailServiceTest {

    @Autowired
    OrderDetailService orderDetailService;

    OrderDetail orderDetailSuccess;
    OrderDetail orderDetailFail;

    @BeforeEach
    public void setup() {
        orderDetailSuccess = new OrderDetail();
        orderDetailSuccess.setOrderDetailId(1L);
        orderDetailSuccess.setOrderId(1L);
        orderDetailSuccess.setSku(new Sku());
        orderDetailSuccess.setAmount(10);

        orderDetailFail = new OrderDetail();
        orderDetailFail.setOrderDetailId(2L);
    }

    @Test
    public void testOrderDetailMake_success() {
        OrderDetail orderDetail = new OrderDetail();

        try {
            orderDetail = orderDetailService.makeOrderDetail(orderDetailSuccess);
        } catch (Exception e) {
            //do Something
        }


        Assertions.assertEquals(1L, orderDetail.getOrderDetailId());
        Assertions.assertEquals(1L, orderDetail.getOrderId());
        Assertions.assertEquals(10, orderDetail.getAmount());
    }

    @Test
    public void testOrderDeatilValidation_success() {
        OrderDetail orderDetail = new OrderDetail();

        try {
            orderDetail = orderDetailService.makeOrderDetail(orderDetailSuccess);
        } catch (Exception e) {
            fail("Should not throw exception.");
        }

        Assertions.assertEquals(1L, orderDetail.getOrderDetailId());
    }

    @Test
    public void testOrderValidation_fail() {
        Exception e = Assertions.assertThrows(Exception.class, () -> {
            orderDetailService.makeOrderDetail(orderDetailFail);
        });

        Assertions.assertEquals("orderDetail Validation Failed.", e.getMessage());
    }
}

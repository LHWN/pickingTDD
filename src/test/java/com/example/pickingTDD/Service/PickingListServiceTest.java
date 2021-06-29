package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.*;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PickingListServiceTest {

    @InjectMocks
    PickingListService pickingListService = new PickingListServiceImpl();

    @Mock
    OrderService orderService;

    Order order;

    @BeforeEach
    void orderSetup() {
        order = new Order();
        order.setOrderId(1L);
        order.setState(OrderStateEnum.ORDERED);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(1L);
        orderDetail.setAmount(10);
        orderDetail.setOrderDetailId(1L);
        orderDetail.setSku(new Sku());

        order.setOrderDetailList(Arrays.asList(orderDetail));
    }

    @Test
    public void testMakePickingList() {
        // mock
        Mockito.when(orderService.changeOrderStatus(Mockito.any(), Mockito.any())).thenReturn(true);

        PickingList assertPickingList = new PickingList();
        assertPickingList.setOrderId(1L);
        assertPickingList.setSkuAmountMap(
                Maps.newHashMap(
                        order.getOrderDetailList().get(0).getSku(),
                        order.getOrderDetailList().get(0).getAmount()));
        assertPickingList.setState("NOTASSIGNED");
        assertPickingList.setPicker(null);

        PickingList pickingList = pickingListService.makePickingList(order);

        Assertions.assertEquals(assertPickingList.getOrderId(), pickingList.getOrderId());
        Assertions.assertEquals("NOTASSIGNED", pickingList.getState());
        Assertions.assertEquals(assertPickingList.getSkuAmountMap().get(order.getOrderDetailList().get(0).getSku()),
                pickingList.getSkuAmountMap().get(order.getOrderDetailList().get(0).getSku()));
        Assertions.assertEquals(OrderStateEnum.LISTMADED, order.getState());
    }
}

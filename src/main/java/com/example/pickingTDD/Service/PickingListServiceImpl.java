package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PickingListServiceImpl implements PickingListService {

    @Autowired
    OrderService orderService;

    @Override
    public PickingList makePickingList(Order order) {
        PickingList pickingList = new PickingList();
        pickingList.setOrderId(order.getOrderId());
        pickingList.setState("NOTASSIGNED");

        Map<Sku, Integer> skuAmountMap = new HashMap<>();
        for(OrderDetail orderDetail : order.getOrderDetailList()) {
            skuAmountMap.put(orderDetail.getSku(), orderDetail.getAmount());
        }

        pickingList.setSkuAmountMap(skuAmountMap);

        // Order Service Change
        orderService.changeOrderService(order, OrderStateEnum.LISTMADED);

        return pickingList;
    }
}

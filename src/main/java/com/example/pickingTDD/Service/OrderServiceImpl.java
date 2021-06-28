package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order makeOrder(Order order) throws Exception {
        if(orderMakeValidation(order)) {
            // order 생성 작업
            return order;
        } else {
            throw new Exception("Order Validation Failed.");
        }
    }

    private boolean orderMakeValidation(Order order) {
        if(order.getOrderId() == null) {
            return false;
        }
        return true;
    }
}

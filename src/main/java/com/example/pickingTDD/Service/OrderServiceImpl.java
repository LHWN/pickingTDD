package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.Order;
import com.example.pickingTDD.Entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderDetailService orderDetailService;

    @Override
    public Order makeOrder(Order order) throws Exception {
        if(orderMakeValidation(order)) {
            for(OrderDetail orderDetail : order.getOrderDetailList()) {
                try {
                    orderDetailService.makeOrderDetail(orderDetail);
                } catch (Exception e) {
                    throw e;
                }
            }
            // order 생성 작업
            return order;
        } else {
            throw new Exception("Order Validation Failed.");
        }
    }

    private boolean orderMakeValidation(Order order) {
        if(order.getOrderId() == null || order.getState() == null || order.getOrderDetailList() == null || order.getOrderDetailList().size() < 1) {
            return false;
        }

        return true;
    }
}

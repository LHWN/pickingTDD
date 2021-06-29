package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.Order;
import com.example.pickingTDD.Entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public OrderDetail makeOrderDetail(OrderDetail orderDetail) throws Exception {
        if(orderDetailValidation(orderDetail)) {
            return orderDetail;
        } else {
            throw new Exception("orderDetail Validation Failed.");
        }
    }

    private boolean orderDetailValidation(OrderDetail orderDetail) {
        if(orderDetail.getOrderId() == null || orderDetail.getSku() == null || orderDetail.getAmount() < 1) {
            return false;
        }

        return true;
    }
}

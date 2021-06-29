package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {
    OrderDetail makeOrderDetail(OrderDetail orderDetail) throws Exception;
}

package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order makeOrder(Order order) throws Exception;
}

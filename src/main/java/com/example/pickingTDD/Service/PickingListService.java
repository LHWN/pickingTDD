package com.example.pickingTDD.Service;

import com.example.pickingTDD.Entity.Order;
import com.example.pickingTDD.Entity.PickingList;

public interface  PickingListService {
    PickingList makePickingList(Order order);
}

package com.example.pickingTDD.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PickingList {
    private Long id;
    private Long orderId;
    private Map<Sku, Integer> skuAmountMap;
    private String state;
    private Picker picker;
}

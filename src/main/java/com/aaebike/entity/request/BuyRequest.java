package com.aaebike.entity.request;

import java.util.List;

import com.aaebike.entity.table.OrderItem;

import lombok.Data;

@Data
public class BuyRequest {
    private Integer userId;

    private Integer expressId;

    private List<OrderItem> productList;
}

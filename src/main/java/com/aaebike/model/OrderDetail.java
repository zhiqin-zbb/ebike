package com.aaebike.model;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetail extends SaleOrder {
    private List<OrderItemDetail> productList;
}

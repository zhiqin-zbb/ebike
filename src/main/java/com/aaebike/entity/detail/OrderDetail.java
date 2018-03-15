package com.aaebike.entity.detail;

import java.util.List;

import com.aaebike.entity.table.Order;

import lombok.Data;

@Data
public class OrderDetail extends Order {
    private List<OrderItemDetail> productList;
}

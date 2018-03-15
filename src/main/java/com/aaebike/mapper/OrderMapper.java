package com.aaebike.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.entity.detail.OrderDetail;
import com.aaebike.entity.table.Order;

@Repository
public interface OrderMapper extends MyMapper<Order> {
    List<OrderDetail> getOrderList(Order saleOrder);
}

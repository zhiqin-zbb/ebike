package com.aaebike.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.entity.detail.OrderItemDetail;
import com.aaebike.entity.table.OrderItem;

@Repository
public interface OrderItemMapper extends MyMapper<OrderItem> {
    List<OrderItemDetail> getOrderItemList(Integer orderId);
}

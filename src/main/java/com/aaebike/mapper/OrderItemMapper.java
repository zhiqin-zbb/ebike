package com.aaebike.mapper;

import java.util.List;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.OrderItem;
import com.aaebike.model.OrderItemDetail;

/**
 * @author zhangbinbin
 * @version 2018/2/12
 */
public interface OrderItemMapper extends MyMapper<OrderItem> {
    List<OrderItemDetail> getOrderItemList(Integer orderId);
}

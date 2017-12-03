package com.aaebike.mapper;

import java.util.List;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.OrderDetail;
import com.aaebike.model.SaleOrder;

public interface OrderMapper extends MyMapper<SaleOrder> {
    List<OrderDetail> getAllOrderDetail(SaleOrder saleOrder);
}

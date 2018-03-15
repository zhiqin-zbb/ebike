package com.aaebike.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaebike.entity.detail.OrderDetail;
import com.aaebike.entity.detail.OrderItemDetail;
import com.aaebike.entity.table.Order;
import com.aaebike.entity.table.OrderItem;
import com.aaebike.mapper.OrderItemMapper;
import com.aaebike.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public Boolean createOrder(Integer userId, Integer expressId, Double price, String randomCode) {
        Order order = new Order();
        order.setUserId(userId);
        order.setExpressId(expressId);
        order.setPrice(price);
        order.setRandomCode(randomCode);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());

        return orderMapper.insert(order) != 0;
    }

    public List<OrderDetail> getOrderList(Order order) {
        if (order.getPage() != null && order.getRows() != null) {
            PageHelper.startPage(order.getPage(), order.getRows());
        }
        return orderMapper.getOrderList(order);
    }

    public Boolean createOrder(Order order) {
        int result = orderMapper.insert(order);
        return result != 0;
    }

    public Boolean saveOrderDetail(OrderItem orderItem) {
        orderItem.setCreateTime(new Date());
        orderItem.setUpdateTime(new Date());
        return orderItemMapper.insert(orderItem) != 0;
    }

    public List<OrderItemDetail> getOrderItemList(Integer orderId) {
        return orderItemMapper.getOrderItemList(orderId);
    }
}

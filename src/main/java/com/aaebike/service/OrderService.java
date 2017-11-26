package com.aaebike.service;

import com.aaebike.mapper.OrderMapper;
import com.aaebike.model.SaleOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;

    public Boolean createOrder(Integer userId, Integer productId, Double price, String randomCode) {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setUserId(userId);
        saleOrder.setProductId(productId);
        saleOrder.setPrice(price);
        saleOrder.setRandomCode(randomCode);
        saleOrder.setCreateTime(new Date());
        saleOrder.setUpdateTime(new Date());

        return orderMapper.insert(saleOrder) != 0;
    }
    public Boolean createOrder(SaleOrder saleOrder) {
        int result = orderMapper.insert(saleOrder);
        return result != 0;
    }
}

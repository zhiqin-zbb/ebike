package com.aaebike.service;

import com.aaebike.mapper.OrderMapper;
import com.aaebike.model.OrderDetail;
import com.aaebike.model.SaleOrder;
import com.aaebike.model.User;
import com.github.pagehelper.PageHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public List<OrderDetail> getAllOrderDetail(SaleOrder saleOrder) {
        if (saleOrder.getPage() != null && saleOrder.getRows() != null) {
            PageHelper.startPage(saleOrder.getPage(), saleOrder.getRows());
        }
        return orderMapper.getAllOrderDetail(saleOrder);
    }

    public Boolean createOrder(SaleOrder saleOrder) {
        int result = orderMapper.insert(saleOrder);
        return result != 0;
    }
}

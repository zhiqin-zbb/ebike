package com.aaebike.model;

import java.util.List;

import lombok.Data;

/**
 * @author zhangbinbin
 * @version 2018/2/12
 */
@Data
public class BuyRequest {
    private Integer userId;

    private Integer expressId;

    private List<OrderItem> productList;
}

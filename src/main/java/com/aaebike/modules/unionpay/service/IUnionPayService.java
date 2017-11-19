package com.aaebike.modules.unionpay.service;

import com.aaebike.common.model.Product;

import java.util.Map;

public interface IUnionPayService {
    /**
     * 银联支付
     */
    String unionPay(Product product);

    /**
     * 前台回调验证
     */
    String validate(Map<String, String> valideData, String encoding);

    /**
     * 对账单下载
     */
    void fileTransfer();
}

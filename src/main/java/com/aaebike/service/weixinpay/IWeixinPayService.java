package com.aaebike.service.weixinpay;

import com.aaebike.model.pay.PayProduct;

public interface IWeixinPayService {
    /**
     * 微信支付下单(模式二)
     * 扫码支付 还有模式一 适合固定商品ID 有兴趣的同学可以自行研究
     */
    String weixinPay2(PayProduct payProduct);

    /**
     * 微信支付下单(模式一)
     */
    void weixinPay1(PayProduct payProduct);

    /**
     * 微信支付退款
     */
    String weixinRefund(PayProduct payProduct);

    /**
     * 关闭订单
     */
    String weixinCloseorder(PayProduct payProduct);

    /**
     * 下载微信账单
     */
    void saveBill();

    /**
     * 微信公众号支付返回一个url地址
     */
    String weixinPayMobile(PayProduct payProduct);

    /**
     * H5支付 唤醒 微信APP 进行支付
     * 申请入口：登录商户平台-->产品中心-->我的产品-->支付产品-->H5支付
     */
    String weixinPayH5(PayProduct payProduct);
}

package com.aaebike.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alipay.demo.trade.config.Configs;
import com.aaebike.common.constants.PayType;
import com.aaebike.common.constants.PayWay;
import com.aaebike.model.pay.PayProduct;
import com.aaebike.service.alipay.IAliPayService;

@SpringBootApplication
@ComponentScan(basePackages={"com.aaebike"})
public class AliPayTest implements CommandLineRunner {
	@Autowired
	private IAliPayService aliPayService;

	public static void main(String[] args) {
		SpringApplication.run(AliPayTest.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Configs.init("zfbinfo.properties");
			PayProduct payProduct = new PayProduct();
			payProduct.setAttach("测试");
			payProduct.setBody("两个苹果八毛钱");
			payProduct.setFrontUrl("https");
			payProduct.setOutTradeNo("20170730");
			payProduct.setPayType(PayType.ALI.getCode());
			payProduct.setPayWay(PayWay.PC.getCode());
			payProduct.setProductId("111111");
			payProduct.setTotalFee("10");
			aliPayService.aliPay(payProduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

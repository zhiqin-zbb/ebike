package com.aaebike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaebike.mapper.CreditCardMapper;
import com.aaebike.model.CreditCard;

/**
 * @author zhangbinbin
 * @version 2018/2/3
 */
@Service
public class CreditCardService {
    @Autowired
    private CreditCardMapper creditCardMapper;

    public int save(CreditCard creditCard) {
        return creditCardMapper.insert(creditCard);
    }

    public CreditCard getByUserId(Integer userId) {
        CreditCard creditCard = new CreditCard();
        creditCard.setUserId(userId);
        creditCard.setDelFlag(0);
        return creditCardMapper.selectOne(creditCard);
    }

    public int updateById(CreditCard creditCard) {
        return creditCardMapper.updateByPrimaryKeySelective(creditCard);
    }
}

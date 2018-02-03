package com.aaebike.controller.api;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aaebike.common.constants.ErrorConstants;
import com.aaebike.model.CreditCard;
import com.aaebike.model.ResponseVo;
import com.aaebike.service.CreditCardService;

/**
 * @author zhangbinbin
 * @version 2018/2/3
 */
@RestController
@RequestMapping("/api")
public class CreditCardApiController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CreditCardService creditCardService;

    @RequestMapping(value = "/credit/save", method = RequestMethod.POST)
    public ResponseVo save(CreditCard creditCard) {
        if (creditCard == null || creditCard.getUserId() == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        CreditCard byUserId = creditCardService.getByUserId(creditCard.getUserId());
        if (byUserId != null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.CREDIT_ALREADY_SAVED);
        } else {
            creditCard.setCreateTime(new Date());
            return ResponseVo.valueOf(creditCardService.save(creditCard) != 0, null, ErrorConstants.SAVE_CREDIT_ERROR);
        }
    }

    @RequestMapping(value = "/credit/getByUserId" ,method = RequestMethod.GET)
    public ResponseVo getByUserId(Integer userId) {
        if (userId == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        CreditCard byUserId = creditCardService.getByUserId(userId);
        return ResponseVo.valueOf(byUserId != null, byUserId, ErrorConstants.CREDIT_NOT_FOUND);
    }

    @RequestMapping(value = "/credit/updateByUserId", method = RequestMethod.POST)
    public ResponseVo updateByUserId(CreditCard creditCard) {
        if (creditCard == null || creditCard.getUserId() == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        CreditCard byUserId = creditCardService.getByUserId(creditCard.getUserId());
        if (byUserId == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.CREDIT_NOT_FOUND);
        } else {
            creditCard.setId(byUserId.getId());
            return ResponseVo.valueOf(creditCardService.updateById(creditCard) != 0, null, ErrorConstants.SAVE_CREDIT_ERROR);
        }
    }
}

package com.aaebike.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aaebike.common.constants.ErrorConstants;
import com.aaebike.entity.base.ResponseVo;
import com.aaebike.entity.table.Express;
import com.aaebike.service.ExpressService;

@RestController
@RequestMapping("/api")
public class ExpressApiController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExpressService expressService;

    @RequestMapping(value = "/express/save", method = RequestMethod.POST)
    public ResponseVo save(Express express) {
        if (express == null || express.getUserId() == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        express.setCreateTime(new Date());
        return ResponseVo.valueOf(expressService.save(express) != 0, null, ErrorConstants.SAVE_EXPRESS_ERROR);
    }

    @RequestMapping(value = "/express/getByUserId", method = RequestMethod.GET)
    public ResponseVo getByUserId(Integer userId) {
        if (userId == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        List<Express> expressList = expressService.getByUserId(userId);
        return ResponseVo.valueOf(expressList != null, expressList, ErrorConstants.EXPRESS_NOT_FOUND);
    }

    @RequestMapping(value = "/express/getById", method = RequestMethod.GET)
    public ResponseVo getById(Integer id) {
        if (id == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        Express byId = expressService.getById(id);
        return ResponseVo.valueOf(byId != null, byId, ErrorConstants.EXPRESS_NOT_FOUND);
    }

    @RequestMapping(value = "/express/updateById", method = RequestMethod.POST)
    public ResponseVo updateById(Express express) {
        if (express == null || express.getId() == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.REQUEST_PARAM_INVALID);
        }

        Express byId = expressService.getById(express.getId());
        if (byId == null) {
            return ResponseVo.valueOf(false, null, ErrorConstants.EXPRESS_NOT_FOUND);
        } else {
            express.setUserId(byId.getUserId());
            return ResponseVo.valueOf(expressService.updateById(express) != 0, null, ErrorConstants.SAVE_EXPRESS_ERROR);
        }
    }
}

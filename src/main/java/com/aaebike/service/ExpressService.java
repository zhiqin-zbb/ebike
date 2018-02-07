package com.aaebike.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaebike.mapper.ExpressMapper;
import com.aaebike.model.Express;

/**
 * @author zhangbinbin
 * @version 2018/2/5
 */
@Service
public class ExpressService {
    @Autowired
    private ExpressMapper expressMapper;

    public int save(Express express) {
        return expressMapper.insert(express);
    }

    public Express getById(Integer id) {
        Express express = new Express();
        express.setId(id);
        express.setDelFlag(0);
        return expressMapper.selectOne(express);
    }

    public List<Express> getByUserId(Integer userId) {
        Express express = new Express();
        express.setUserId(userId);
        express.setDelFlag(0);
        return expressMapper.select(express);
    }

    public int updateById(Express express) {
        return expressMapper.updateByPrimaryKeySelective(express);
    }
}

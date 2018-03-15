package com.aaebike.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaebike.entity.table.Express;
import com.aaebike.mapper.ExpressMapper;

@Service
public class ExpressService {
    @Autowired
    private ExpressMapper expressMapper;

    public int save(Express express) {
        // 新增时设为默认，需要取消之前的默认项
        if (express.getDefaultFlag() != null && express.getDefaultFlag() == 1) {
            expressMapper.cancelDefaultByUserId(express.getUserId());
        }
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
        // 更新时设为默认，需要取消之前的默认项
        if (express.getDefaultFlag() != null && express.getDefaultFlag() == 1) {
            expressMapper.cancelDefaultByUserId(express.getUserId());
        }

        return expressMapper.updateByPrimaryKeySelective(express);
    }
}

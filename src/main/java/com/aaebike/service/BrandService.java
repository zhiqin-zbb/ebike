package com.aaebike.service;

import com.aaebike.mapper.BrandMapper;
import com.aaebike.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public List<Brand> getAllBrandList() {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", 0);
        return brandMapper.selectByExample(example);
    }

    public List<Brand> getActiveBrandList() {
        return brandMapper.getActiveBrandList();
    }
}

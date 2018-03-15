package com.aaebike.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaebike.entity.table.Brand;
import com.aaebike.mapper.BrandMapper;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

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

    public List<Brand> getBrandList(Brand brand) {
        if (brand.getPage() != null && brand.getRows() != null) {
            PageHelper.startPage(brand.getPage(), brand.getRows());
        }

        return brandMapper.getActiveBrandList();
    }
}

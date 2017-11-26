package com.aaebike.mapper;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.Brand;

import java.util.List;

public interface BrandMapper extends MyMapper<Brand> {
    List<Brand> getActiveBrandList();
}

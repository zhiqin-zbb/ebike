package com.aaebike.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.entity.table.Brand;

@Repository
public interface BrandMapper extends MyMapper<Brand> {
    List<Brand> getActiveBrandList();
}

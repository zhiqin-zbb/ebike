package com.aaebike.mapper;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.City;

public interface CityMapper extends MyMapper<City> {
    City query(Integer id);
}

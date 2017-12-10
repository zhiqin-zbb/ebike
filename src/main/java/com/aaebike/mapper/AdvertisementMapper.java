package com.aaebike.mapper;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.Advertisement;

import java.util.List;

public interface AdvertisementMapper extends MyMapper<Advertisement> {
    public List<String> getAllAdImgUrlList();
}

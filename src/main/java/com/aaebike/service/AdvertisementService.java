package com.aaebike.service;

import com.aaebike.mapper.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    AdvertisementMapper advertisementMapper;

    public List<String> getAllAdImgUrlList() {
        return advertisementMapper.getAllAdImgUrlList();
    }
}

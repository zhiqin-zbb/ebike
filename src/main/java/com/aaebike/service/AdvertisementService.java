package com.aaebike.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaebike.entity.result.AdvertisementResult;
import com.aaebike.entity.table.Advertisement;
import com.aaebike.entity.table.AdvertisementType;
import com.aaebike.mapper.AdvertisementMapper;

@Service
public class AdvertisementService {
    @Autowired
    AdvertisementMapper advertisementMapper;

    public List<String> getCarouselImgUrlList() {
        return advertisementMapper.getCarouselImgUrlList();
    }

    public List<AdvertisementResult> getAdvertisementList() {
        List<AdvertisementResult> advertisementResultList = new LinkedList<>();

        List<AdvertisementType> advTypeList = advertisementMapper.getAdvTypeList();
        if (CollectionUtils.isNotEmpty(advTypeList)) {
            for (AdvertisementType advertisementType : advTypeList) {
                List<Advertisement> advList = advertisementMapper.getAdvList(advertisementType.getId());
                if (CollectionUtils.isNotEmpty(advList)) {
                    AdvertisementResult advertisementResult = new AdvertisementResult();
                    advertisementResult.setTypeName(advertisementType.getName());
                    advertisementResult.setAdvertisementList(advList);
                    advertisementResultList.add(advertisementResult);
                }
            }
        }

        return advertisementResultList;
    }
}

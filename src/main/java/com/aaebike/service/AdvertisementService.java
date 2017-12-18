package com.aaebike.service;

import com.aaebike.mapper.AdvertisementMapper;
import com.aaebike.model.Advertisement;
import com.aaebike.model.AdvertisementType;
import com.aaebike.model.result.AdvertisementResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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

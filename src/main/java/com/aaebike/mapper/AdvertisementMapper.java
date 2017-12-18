package com.aaebike.mapper;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.model.Advertisement;
import com.aaebike.model.AdvertisementType;

import java.util.List;

public interface AdvertisementMapper extends MyMapper<Advertisement> {
    List<String> getCarouselImgUrlList();

    List<AdvertisementType> getAdvTypeList();

    List<Advertisement> getAdvList(Integer typeId);
}

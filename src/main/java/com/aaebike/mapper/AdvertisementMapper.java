package com.aaebike.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaebike.common.utils.MyMapper;
import com.aaebike.entity.table.Advertisement;
import com.aaebike.entity.table.AdvertisementType;

@Repository
public interface AdvertisementMapper extends MyMapper<Advertisement> {
    List<String> getCarouselImgUrlList();

    List<AdvertisementType> getAdvTypeList();

    List<Advertisement> getAdvList(Integer typeId);
}

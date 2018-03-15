package com.aaebike.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aaebike.common.constants.ErrorConstants;
import com.aaebike.entity.base.ResponseVo;
import com.aaebike.entity.result.AdvertisementResult;
import com.aaebike.service.AdvertisementService;

@RestController
@RequestMapping("/api")
public class AdvertisementApiController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping(value = "/adv/carousel", method = RequestMethod.GET)
    public ResponseVo getCarouselImgUrlList() {
        List<String> carouselImgUrlList = advertisementService.getCarouselImgUrlList();
        return ResponseVo.valueOf(CollectionUtils.isNotEmpty(carouselImgUrlList), carouselImgUrlList, ErrorConstants.CAROUSEL_NOT_FOUND);
    }

    @RequestMapping(value = "/adv/list", method = RequestMethod.GET)
    public ResponseVo getAdvList() {
        List<AdvertisementResult> advertisementList = advertisementService.getAdvertisementList();
        return ResponseVo.valueOf(CollectionUtils.isNotEmpty(advertisementList), advertisementList, ErrorConstants.ADVERTISEMENT_NOT_FOUND);
    }
}

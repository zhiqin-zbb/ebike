package com.aaebike.controller.api;

import com.aaebike.common.constants.ErrorConstants;
import com.aaebike.model.ResponseVo;
import com.aaebike.model.result.AdvertisementResult;
import com.aaebike.service.AdvertisementService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

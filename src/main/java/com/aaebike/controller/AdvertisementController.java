package com.aaebike.controller;

import com.aaebike.model.AdvertisementType;
import com.aaebike.model.result.AdvertisementResult;
import com.aaebike.service.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/adv")
public class AdvertisementController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping("/carousel")
    @ResponseBody
    public List<String> getCarouselImgUrlList() {
        return advertisementService.getCarouselImgUrlList();
    }

    @RequestMapping("/advertisementList")
    @ResponseBody
    public List<AdvertisementResult> getAdvList() {
        return advertisementService.getAdvertisementList();
    }
}

package com.aaebike.model.result;

import com.aaebike.model.Advertisement;
import lombok.Data;

import java.util.List;

@Data
public class AdvertisementResult {
    private String typeName;

    private List<Advertisement> advertisementList;
}
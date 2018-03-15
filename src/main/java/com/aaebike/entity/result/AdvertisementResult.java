package com.aaebike.entity.result;

import java.util.List;

import com.aaebike.entity.table.Advertisement;

import lombok.Data;

@Data
public class AdvertisementResult {
    private String typeName;

    private List<Advertisement> advertisementList;
}
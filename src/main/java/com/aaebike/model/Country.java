package com.aaebike.model;

import lombok.Data;

@Data
public class Country extends BaseEntity {
    private String countryname;

    private String countrycode;
}
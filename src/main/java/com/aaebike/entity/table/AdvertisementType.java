package com.aaebike.entity.table;

import javax.persistence.Column;
import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "advertisement_type")
public class AdvertisementType extends TableBaseEntity {
    @Column(name = "name")
    private String name;
}

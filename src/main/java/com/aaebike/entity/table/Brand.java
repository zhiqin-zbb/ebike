package com.aaebike.entity.table;

import javax.persistence.Column;
import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "brand")
public class Brand extends TableBaseEntity {
    @Column(name = "name")
    private String name;

    public static Brand initAllBrand() {
        Brand allBrand = new Brand();
        allBrand.setId(0);
        allBrand.setName("所有品牌");
        return allBrand;
    }
}

package com.aaebike.entity.table;

import javax.persistence.Column;
import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "advertisement")
public class Advertisement extends TableBaseEntity {
    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "type_id")
    private int typeId;
}

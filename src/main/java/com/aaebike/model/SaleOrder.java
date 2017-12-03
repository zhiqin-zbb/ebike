package com.aaebike.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.aaebike.common.date.format.JackJsonDateTimeFormat;
import com.aaebike.common.date.format.JackJsonDateTimeParse;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SaleOrder extends BaseEntity {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 下单价格
     */
    private Double price;

    /**
     * 随机码
     */
    private String randomCode;

    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    private Date createTime;

    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    private Date updateTime;

    private int delFlag;
}

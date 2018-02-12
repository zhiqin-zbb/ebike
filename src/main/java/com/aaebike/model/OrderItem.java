package com.aaebike.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.aaebike.common.date.format.JackJsonDateTimeFormat;
import com.aaebike.common.date.format.JackJsonDateTimeParse;

import lombok.Data;

/**
 * @author zhangbinbin
 * @version 2018/2/12
 */
@Data
public class OrderItem extends BaseEntity {
    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 数量
     */
    private Integer count;

    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    private Date createTime;

    @JsonDeserialize(using = JackJsonDateTimeParse.class)
    @JsonSerialize(using = JackJsonDateTimeFormat.class)
    private Date updateTime;

    private int delFlag;
}

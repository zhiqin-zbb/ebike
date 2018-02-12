package com.aaebike.model;

import java.util.Date;

import lombok.Data;

/**
 * @author zhangbinbin
 * @version 2018/2/5
 */
@Data
public class Express extends BaseEntity {
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否默认地址，0-否，1-是
     */
    private Integer defaultFlag;

    private Date createTime;

    private Date updateTime;

    private int delFlag;
}

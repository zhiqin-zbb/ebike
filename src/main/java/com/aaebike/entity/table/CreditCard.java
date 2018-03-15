package com.aaebike.entity.table;

import javax.persistence.Table;

import com.aaebike.entity.base.TableBaseEntity;

import lombok.Data;

@Data
@Table(name = "credit_card")
public class CreditCard extends TableBaseEntity {
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别，0-男，1-女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String tel;

    /**
     * QQ号码
     */
    private String qq;

    /**
     * 地址
     */
    private String address;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 正面url
     */
    private String frontSideUrl;

    /**
     * 反面url
     */
    private String backSideUrl;
}

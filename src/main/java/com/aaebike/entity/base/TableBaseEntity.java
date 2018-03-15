package com.aaebike.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
public abstract class TableBaseEntity {
    /**
     * 主键字段
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 数据是否已删除:0:为删除,1：已删除
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;
}

package com.nwp.appcloudconsumer.entity;

import lombok.Data;

@Data
public class BaseEntity {

    /**创建人*/
    private String createdBy;
    /**创建时间*/
    private String createdTime;
    /**修改人*/
    private String updatedBy;
    /**修改时间*/
    private String updatedTime;
}
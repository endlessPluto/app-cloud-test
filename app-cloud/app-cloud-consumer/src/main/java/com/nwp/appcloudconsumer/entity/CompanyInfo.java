package com.nwp.appcloudconsumer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyInfo extends BaseEntity implements Serializable{

    /**公司编码*/
    private String companyCode;
    /**公司名称*/
    private String companyName;
    /**公司简称*/
    private String shortName;
    /**公司地址*/
    private String companyAddress;
    /**城市编码*/
    private String cityCode;
    /**是否生效*/
    private String isVaild;

}
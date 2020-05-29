package com.nwp.appcloudconsumer.service;

import com.nwp.appcloudconsumer.entity.CompanyInfo;
import com.nwp.entity.ResultInfo;

public interface ICompanyService {
    ResultInfo getAllList(CompanyInfo companyInfo);
}
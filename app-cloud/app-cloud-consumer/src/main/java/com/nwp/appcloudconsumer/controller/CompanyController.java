package com.nwp.appcloudconsumer.controller;

import com.nwp.appcloudconsumer.entity.CompanyInfo;
import com.nwp.appcloudconsumer.service.ICompanyService;
import com.nwp.entity.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公司接口
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    /**
     * 列表获取
     * @param companyInfo
     * @return
     */
    @RequestMapping("/getAllList")
    @ResponseBody
    public ResultInfo getAllList(CompanyInfo companyInfo){
        return companyService.getAllList(companyInfo);
    }

}
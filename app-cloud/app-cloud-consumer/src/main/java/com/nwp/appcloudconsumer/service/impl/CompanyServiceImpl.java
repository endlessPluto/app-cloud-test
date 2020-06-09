package com.nwp.appcloudconsumer.service.impl;

import com.nwp.appcloudconsumer.dao.ICompanyDao;
import com.nwp.appcloudconsumer.entity.CompanyInfo;
import com.nwp.appcloudconsumer.service.ICompanyService;
import com.nwp.entity.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    public final static Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private ICompanyDao companyDao;

    @Override
    public ResultInfo getAllList(CompanyInfo companyInfo) {
        try {
            List<CompanyInfo> list = companyDao.getAllList(companyInfo);
            int count = companyDao.getAllListCount(companyInfo);
        } catch (Exception e) {
            logger.info("列表获取异常:"+e);
        }


        return new ResultInfo();
    }

}
package com.nwp.appcloudconsumer.service.impl;

import com.nwp.appcloudconsumer.dao.IHeatStationDao;
import com.nwp.appcloudconsumer.entity.HeatStationInfo;
import com.nwp.appcloudconsumer.service.IHeatStationService;
import com.nwp.entity.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HeatStationServiceImpl implements IHeatStationService {

    @Autowired
    private IHeatStationDao heatStationDao;

    @Override
    public ResultInfo queryListAll(HeatStationInfo heatStationInfo) {
        return new ResultInfo(0,"成功",0);
    }
}
package com.nwp.appcloudconsumer.service;

import com.nwp.appcloudconsumer.entity.HeatStationInfo;
import com.nwp.entity.ResultInfo;

public interface IHeatStationService {

    ResultInfo queryListAll(HeatStationInfo heatStationInfo);

}
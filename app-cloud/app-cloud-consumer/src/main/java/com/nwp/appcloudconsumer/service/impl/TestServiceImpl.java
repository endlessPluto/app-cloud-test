package com.nwp.appcloudconsumer.service.impl;

import com.nwp.appcloudconsumer.dao.ITestDao;
import com.nwp.appcloudconsumer.entity.Student;
import com.nwp.appcloudconsumer.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements ITestService{

    @Autowired
    private ITestDao testDao;

    @Override
    public List<Student> getList() {
        List<Student> list = testDao.getList();
        return list;
    }
}
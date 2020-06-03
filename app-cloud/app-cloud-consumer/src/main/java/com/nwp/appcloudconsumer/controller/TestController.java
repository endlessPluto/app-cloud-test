package com.nwp.appcloudconsumer.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.nwp.appcloudconsumer.entity.Student;
import com.nwp.appcloudconsumer.service.ITestService;
import com.nwp.util.DateUtils;
import com.nwp.util.IpconfigUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
@Log4j2
public class TestController {

    @Autowired
    private ITestService testService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 列表测试
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public List<Student> getList(HttpServletRequest request) throws Exception {
        String ipAddress = IpconfigUtils.getIpAddress();
        String ip = IpconfigUtils.getIp(request);
        String macAddress = IpconfigUtils.getMacAddress();
        String os = IpconfigUtils.getOS();
        System.out.println("服务端"+ipAddress);
        System.out.println("客户端"+ip);
        System.out.println("macAddress"+macAddress);
        System.out.println("操作系统"+os);
        List<Student> list = testService.getList();
        return list;
    }

    /**
     * 测试redis
     * @return
     */
    @RequestMapping("/redisGetDemo")
    @ResponseBody
    public Object redisGetDemo() {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("name", "shaung");
        Object name = ops.get("name");
        return name;
    }

    /**
     * 导出测试
     */
    @RequestMapping("/simpleWrite")
    @ResponseBody
    public void simpleWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String date = DateUtils.getDate();
        String fileName = "测试导出1-" + date;
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(getOutputStream(fileName, response), Student.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(testService.getList(), writeSheet);
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 导出文件时为Writer生成OutputStream
     *
     * @param fileName
     * @param response
     * @return
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }

    /**
     * 测试数据
     * @return
     */
    private List<Student> data() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student stu = new Student();
            stu.setId(String.valueOf(i));
            stu.setName("字符串"+i);
            list.add(stu);
        }
        return list;
    }

}
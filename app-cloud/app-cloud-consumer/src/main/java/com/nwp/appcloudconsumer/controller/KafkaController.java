package com.nwp.appcloudconsumer.controller;

import com.nwp.appcloudconsumer.kafka.KafkaProducer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/kafka")
@RestController
@Slf4j
@Api(value = "SwaggerValue",tags = {"KafkaController"})
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(){
        kafkaProducer.send("这是kafka发送的一条信息");
    }

}

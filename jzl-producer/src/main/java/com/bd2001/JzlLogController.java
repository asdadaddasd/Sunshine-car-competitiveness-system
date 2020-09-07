package com.bd2001;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class JzlLogController {
    @Resource
    private KafkaTemplate<String,String>  kafkaTemplate;

    @GetMapping("/sendMessage") // 映射请求
    @ResponseBody
    public String  sendMessage() throws Exception{
        //获得日志
        List<List<ChannelOrderInfo>> Lists = SqApplication.log();
        //开一个线程发送消息
        new Thread(()->{
            if (Lists != null){
                for (List<ChannelOrderInfo> logs : Lists) {
                    if (logs != null){
                        for (ChannelOrderInfo log : logs) {
                            if (log != null) {
                                System.out.println("++++++++++++++++++++++++++++++++++++++++");
                                System.out.println(log.toString());
                                kafkaTemplate.send("jzl-log",log.toString());
                            }
                        }
                    }

                }
            }
        }).start();
        //  kafkaTemplate.send("jzl-log","abcd");
        return "发送成功!";
    }

}

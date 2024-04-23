package com.xxx.mng.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author a1234
 * @description
 * @create 2024-01-12 21:16
 */
//@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test_flink_2_topic")
    public void consumeTopic(String msg) {
        // 参数: 从topic中收到的 value值
        System.out.println("收到的信息: " + msg);
    }

}

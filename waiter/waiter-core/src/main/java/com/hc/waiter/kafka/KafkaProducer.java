package com.hc.waiter.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Random;

/**
 * @author a1234
 * @description
 * @create 2024-01-14 18:37
 */
//@Component
public class KafkaProducer {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @EventListener(ApplicationStartedEvent.class)
    public void startSend() {
        taskExecutor.execute(() -> {
            Random random = new Random();
            while (true) {
                try {
                    // 生成一个指定范围内的随机整数（包括边界值）
                    int minValue = 1;
                    int maxValue = 200;
                    int randomInt = random.nextInt(maxValue - minValue + 1) + minValue;
                    System.out.println("随机整数：" + randomInt);
                    send(randomInt+"");
                    Thread.sleep(randomInt);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void send(String msg) {
        // 发送消息
        kafkaTemplate.send("test_flink_topic", msg);
    }

}

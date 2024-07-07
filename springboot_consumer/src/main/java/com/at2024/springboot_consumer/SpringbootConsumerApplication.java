package com.at2024.springboot_consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootConsumerApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringbootConsumerApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringbootConsumerApplication.class, args);
            logger.info("****************************************集成springboot消费者启动成功****************************************");
        } catch (Exception e) {
            logger.info("****************************************集成springboot消费者启动失败****************************************",e);
        }
    }

}

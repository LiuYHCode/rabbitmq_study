package com.at2024.confirm_consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfirmConsumerApplication {

    private static Logger logger = LoggerFactory.getLogger(ConfirmConsumerApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(ConfirmConsumerApplication.class, args);
            logger.info("****************************************确认机制消费者启动成功****************************************");
        } catch (Exception e) {
            logger.info("****************************************确认机制消费者启动失败****************************************",e);
        }
    }

}

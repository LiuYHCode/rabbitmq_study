package com.at2024.confirm_producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfirmProducerApplication {

    private static Logger logger = LoggerFactory.getLogger(ConfirmProducerApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(ConfirmProducerApplication.class, args);
            logger.info("****************************************确认机制生产者启动成功****************************************");
        } catch (Exception e) {
            logger.info("****************************************确认机制生产者启动失败****************************************",e);
        }
    }

}

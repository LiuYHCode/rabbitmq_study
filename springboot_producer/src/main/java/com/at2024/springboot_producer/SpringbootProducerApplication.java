package com.at2024.springboot_producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootProducerApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringbootProducerApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringbootProducerApplication.class, args);
            logger.info("****************************************集成springboot生产者启动成功****************************************");
        } catch (Exception e) {
            logger.info("****************************************集成springboot生产者启动失败****************************************",e);
        }
    }

}

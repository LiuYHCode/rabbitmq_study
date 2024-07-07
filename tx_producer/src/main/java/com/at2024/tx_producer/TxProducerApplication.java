package com.at2024.tx_producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TxProducerApplication {

    private static Logger logger = LoggerFactory.getLogger(TxProducerApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(TxProducerApplication.class, args);
            logger.info("****************************************事务提交机制生产者启动成功****************************************");
        } catch (Exception e) {
            logger.info("****************************************事务提交机制生产者启动失败****************************************",e);
        }
    }

}

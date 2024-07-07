package com.at2024.confirm_producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfirmProducerApplicationTests {

    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试成功发送消息的案例
     */
    @Test
    void test01SendMessage() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT,
                ROUTING_KEY,
                "Hello at2024");
    }

    /**
     * 测试发送到交换机失败案例
     */
    @Test
    void test02SendMessage() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT + "~",
                ROUTING_KEY,
                "Hello at2024");
    }

    /**
     * 测试发送到交换机失败案例
     */
    @Test
    void test03SendMessage() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT,
                ROUTING_KEY + "~",
                "Hello at2024");
    }

}

package com.at2024.confirm_producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfirmProducerApplicationTests {

    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";
    public static final String STOCK_ROUTING_KEY = "stock";

    public static final String EXCHANGE_NORMAL = "exchange.normal.video";
    public static final String EXCHANGE_DEAD_LETTER = "exchange.dead.letter.video";

    public static final String ROUTING_KEY_NORMAL = "routing.key.normal.video";
    public static final String ROUTING_KEY_DEAD_LETTER = "routing.key.dead.letter.video";

    public static final String QUEUE_NORMAL = "queue.normal.video";
    public static final String QUEUE_DEAD_LETTER = "queue.dead.letter.video";

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

    @Test
    public void test04SendMessage() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT,
                ROUTING_KEY,
                "Hello at2024_" + i);
        }
    }

    @Test
    public void test05SendMessage() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT,
                STOCK_ROUTING_KEY,
                "Hello at2024_" + i);
        }
    }

    @Test
    public void testSendMessageTTL() {

        // 1、创建消息后置处理器对象
        MessagePostProcessor messagePostProcessor = (Message message) -> {
            // 设定 TTL 时间，以毫秒为单位
            message.getMessageProperties().setExpiration("5000");
            return message;
        };

        // 2、发送消息
        rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT,
                STOCK_ROUTING_KEY,
                "Hello at2024", messagePostProcessor);
    }

    @Test
    public void testSendMessageButReject() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_NORMAL,
                ROUTING_KEY_NORMAL,
                "测试死信情况1：消息被拒绝");
    }

    @Test
    public void testSendMessageOutOfMaxLen() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_NORMAL,
                    ROUTING_KEY_NORMAL,
                    "测试死信情况2" + i);
        }
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_NORMAL,
                    ROUTING_KEY_NORMAL,
                    "测试死信情况2：消息超过队列最大容量" + i);
        }
    }
}

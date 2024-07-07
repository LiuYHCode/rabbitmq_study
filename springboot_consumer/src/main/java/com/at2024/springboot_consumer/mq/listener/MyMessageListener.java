package com.at2024.springboot_consumer.mq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassDescription:
 * @Author: lyh
 * @Created: 2024/7/6 23:28
 */
@Component
@Slf4j
public class MyMessageListener {

    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";
    public static final String QUEUE_NAME  = "queue.order";

    //使用QueueBinding，绑定队列、交换机、路由键，如果没有的话会先进行创建
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = QUEUE_NAME, durable = "true"),
//            exchange = @Exchange(value = EXCHANGE_DIRECT),
//            key = {ROUTING_KEY}
//    ))
    //直接监听队列，前提是队列和交换机都存在的情况下
    @RabbitListener(queues = {QUEUE_NAME})
    public void processMessage(String dateString,
                               Message message,
                               Channel channel) {
        log.info(dateString);
    }
}

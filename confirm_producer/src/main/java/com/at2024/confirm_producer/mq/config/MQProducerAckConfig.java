package com.at2024.confirm_producer.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassDescription:
 * @Author: lyh
 * @Created: 2024/7/7 13:50
 */
@Component
@Slf4j
public class MQProducerAckConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("confirm() 回调函数打印 correlationData:" + correlationData);
        log.info("confirm() 回调函数打印 ack:" + ack);
        log.info("confirm() 回调函数打印 cause:" + cause);
        if (ack) {
            log.info("消息发送到交换机成功！数据：" + correlationData);
        } else {
            log.info("消息发送到交换机失败！数据：" + correlationData + " 原因：" + cause);
        }
    }

    //该方法用于处理消息发送到队列失败的情况
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info("消息主体: " + new String(returnedMessage.getMessage().getBody()));
        log.info("应答码: " + returnedMessage.getReplyCode());
        log.info("描述：" + returnedMessage.getReplyText());
        log.info("消息使用的交换器 exchange : " + returnedMessage.getExchange());
        log.info("消息使用的路由键 routing : " + returnedMessage.getRoutingKey());
    }
}

package com.at2024.rabbitmq_basics.work;

import com.at2024.rabbitmq_basics.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassDescription:
 * @Author: lyh
 * @Created: 2024/7/3 22:53
 */
public class Consumer1 {
    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        /**
         * 匿名内部类的方式重写其 handleDelivery 方法来处理接收到的消息
         */
        Consumer consumer = new DefaultConsumer(channel){
            /**
             * @param consumerTag 这是消费者标签，是用于标识消费者的唯一字符串。当你声明一个消费者时，可以为它指定一个 consumerTag，如果没有指定，则 RabbitMQ 会自动生成一个
             * @param envelope 信封对象，包含了消息的元数据，比如消息的路由键（routing key）、交换机（exchange）等
             * @param properties 消息的基本属性
             * @param body 消息体
             * @throws IOException
             */
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer1 body："+new String(body));
            }
        };

        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}

package com.at2024.rabbitmq_basics.topic;

import com.at2024.rabbitmq_basics.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassDescription:
 * @Author: lyh
 * @Created: 2024/7/3 23:49
 */
public class Consumer2 {
    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        String QUEUE_NAME = "test_topic_queue2";

        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        Consumer consumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body："+new String(body));
            }
        };

        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}

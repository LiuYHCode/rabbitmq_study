package com.at2024.rabbitmq_basics.routing;

import com.at2024.rabbitmq_basics.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassDescription:
 * @Author: lyh
 * @Created: 2024/7/3 23:27
 */
public class Consumer1 {
    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        String queue1Name = "test_direct_queue1";

        channel.queueDeclare(queue1Name,true,false,false,null);

        Consumer consumer = new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body："+new String(body));
                System.out.println("Consumer1 将日志信息打印到控制台.....");
            }
        };
        channel.basicConsume(queue1Name,true,consumer);
    }
}

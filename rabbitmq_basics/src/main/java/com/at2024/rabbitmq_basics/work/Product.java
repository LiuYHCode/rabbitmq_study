package com.at2024.rabbitmq_basics.work;

import com.at2024.rabbitmq_basics.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassDescription:
 * @Author: lyh
 * @Created: 2024/7/3 22:51
 */
public class Product {
    public static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        for (int i = 1; i <= 10; i++) {
            String body = i+"hello rabbitmq~~~";
            channel.basicPublish("",QUEUE_NAME,null,body.getBytes());
        }

        channel.close();

        connection.close();
    }
}

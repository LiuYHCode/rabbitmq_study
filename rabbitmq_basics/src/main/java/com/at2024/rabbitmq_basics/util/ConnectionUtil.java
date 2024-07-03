package com.at2024.rabbitmq_basics.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    public static final String HOST_ADDRESS = "192.168.0.145";

    public static Connection getConnection() throws Exception {

        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 设置服务地址
        factory.setHost(HOST_ADDRESS);

        // 端口
        factory.setPort(5672);

        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/");
        factory.setUsername("lyh");
        factory.setPassword("aaa111");

        // 通过工程获取连接
        Connection connection = factory.newConnection();

        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection con = ConnectionUtil.getConnection();
        // amqp://lyh@192.168.0.145:5672/
        System.out.println(con);
        con.close();
    }
}
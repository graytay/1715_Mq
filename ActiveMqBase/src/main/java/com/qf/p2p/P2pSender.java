package com.qf.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class P2pSender {
    private String username;
    private String password;
    private String url;

    public P2pSender() {
        username= ActiveMQConnection.DEFAULT_USER;
        password=ActiveMQConnection.DEFAULT_PASSWORD;
        url="tcp://10.0.127.39:61616";
    }
    //发送者
    public  void send(String msg)throws Exception{

        //1、创建连接工厂
        ConnectionFactory connectionFactory=new  ActiveMQConnectionFactory(username,password,url);
        //2、创建连接对
        Connection connection=connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、获取会话对象，第一个参数表明是否开启事务，第二个参数表示消息的应答模式
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5、创建目标对象，要么是队列，要么是主题
        Destination destination=session.createQueue("java");
        //6、创建消息提供者
        MessageProducer messageProducer=session.createProducer(destination);
        //7、创建消息
        TextMessage message = session.createTextMessage(msg);
        //8、发送消息
        messageProducer.send(message);
        //关闭
        session.close();
        connection.close();
    }
    public void sendRules(int age,int qq) throws JMSException {
        //创建连接工厂
        ConnectionFactory factory=new ActiveMQConnectionFactory(username,password,url);
        //创建连接
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();
        //获取会话对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目标对象
        Destination destination = session.createQueue("java");
        //创建消息提供者
        MessageProducer producer = session.createProducer(destination);
        //创建消息
        MapMessage mapMessage=session.createMapMessage();
        mapMessage.setIntProperty("age",age);
        mapMessage.setIntProperty("qq",qq);
        producer.send(mapMessage);
        session.close();
        connection.close();
    }
}

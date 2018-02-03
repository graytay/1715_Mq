package com.qf.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class P2pReceiver {
    private String username;
    private String password;
    private String url;

    public P2pReceiver() {
        username= ActiveMQConnection.DEFAULT_USER;
        password=ActiveMQConnection.DEFAULT_PASSWORD;
        url="tcp://10.0.127.39:61616";
    }

    public static void main(String[] args) {
        try {

           //new P2pReceiver().recveiveJson();
            new P2pReceiver().receiveRules();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public String receive()throws Exception{
        //1、创建连接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(username,password,url);
        //2、创建连接对
        Connection connection=connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、获取会话对象   第一个参数代表事务，第二个参数代表消息的应答模式
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5、创建目标对象，要么是队列，要么是主题
        Destination destination=session.createQueue("java");
        //6、创建消息接收者
        MessageConsumer consumer=session.createConsumer(destination);
        //7、接收消息
        TextMessage message= (TextMessage) consumer.receive();
        String msg=message.getText();
        session.close();
        connection.close();
        return msg;
    }
    //接收json
    public void recveiveJson() throws JMSException {
        //创建连接工厂
        ConnectionFactory factory=new ActiveMQConnectionFactory(username,password,url);
        //创建连接对
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();
        //获取会话对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目标对象
        Destination destination = session.createQueue("java");
        //创建消息接收者
        MessageConsumer consumer = session.createConsumer(destination);
        //设置消息监听器
        consumer.setMessageListener(new MsgFilter());
        session.close();
        connection.close();
    }
    //条件接收
    public void receiveRules() throws JMSException {
        //创建连接工厂
        ConnectionFactory factory=new ActiveMQConnectionFactory(username,password,url);
        //创建连接
        Connection connection = factory.createConnection();
        //开启连接
        connection.start();
        //获取会话对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目标对象
        Destination destination=session.createQueue("java");
        //创建消息接收者
        MessageConsumer consumer = session.createConsumer(destination,"age>18");
        consumer.setMessageListener(new MsgFilter());

    }
}

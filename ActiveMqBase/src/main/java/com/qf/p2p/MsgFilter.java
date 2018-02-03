package com.qf.p2p;

import com.google.gson.Gson;
import com.qf.common.ResultJson;
import com.qf.pojo.City;

import javax.jms.*;

public class MsgFilter implements MessageListener{

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage){
            TextMessage textMessage= (TextMessage) message;
            try {
                String json=textMessage.getText();
                System.out.println("原生"+json);
                ResultJson<City> resultJson= new Gson().fromJson(json,ResultJson.class);
                System.out.println(resultJson.getStatus()+""+resultJson.getResult().size());
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
        if (message instanceof MapMessage){
            MapMessage mapMessage= (MapMessage) message;
            try {
                System.out.println(mapMessage.getIntProperty("age")+"*******"+mapMessage.getIntProperty("qq"));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

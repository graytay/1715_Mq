package com.qf.p2p;

public class P2pMain {
    public static void main(String[] args) throws Exception {
       // new  P2pSender().send("java你好");
       // System.out.println(new P2pReceiver().receive());
        /*new P2pSender().send("{\"status\":\"0\",\"msg\":\"ok\",\"result\":[{\"city\":\"beijing\",\"cityname\":\"北京\"},{\"city\":\"tianjin\",\"cityname\":\"天津\"},{\"city\":\"hangzhou\",\"cityname\":\"杭州\"},{\"city\":\"chengdu\",\"cityname\":\"成都\"},{\"city\":\"lanzhou\",\"cityname\":\"兰州\"},{\"city\":\"guiyang\",\"cityname\":\"贵阳\"},{\"city\":\"nanchang\",\"cityname\":\"南昌\"},{\"city\":\"changchun\",\"cityname\":\"长春\"},{\"city\":\"haerbin\",\"cityname\":\"哈尔滨\"},{\"city\":\"wuhan\",\"cityname\":\"武汉\"},{\"city\":\"shanghai\",\"cityname\":\"上海\"},{\"city\":\"shenzhen\",\"cityname\":\"深圳\"}]}");*/
        for (int i=15;i<30;i++){
            new P2pSender().sendRules(i,124+i);
        }
    }
}

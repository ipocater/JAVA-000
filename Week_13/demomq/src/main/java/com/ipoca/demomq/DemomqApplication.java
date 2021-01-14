package com.ipoca.demomq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

import java.util.concurrent.atomic.AtomicInteger;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;


//@SpringBootApplication
public class DemomqApplication {

    public static void main(String[] args) {

        Destination destination = new ActiveMQTopic("test.topic");

        Destination destination1 = new ActiveMQQueue("test.queue");

        testDestination(destination);

        testDestination(destination1);

    }

    public static void testDestination(Destination destination){

        try {
            //创建连接和会话
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://121.4.76.225:61617");
            ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
            conn.start();
            Session session = conn.createSession(false,AUTO_ACKNOWLEDGE);

            //创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            final AtomicInteger count = new AtomicInteger(0);
            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("消息序号：" + count.getAndIncrement() + " =>recive from " + destination.toString() + ":" + message);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            consumer.setMessageListener(listener);

            //创建生产者 生产100条消息
            MessageProducer producer = session.createProducer(destination);
            int index = 0;
            while (index++ < 100){
                TextMessage message = session.createTextMessage(index + "message");
                producer.send(message);
            }

            Thread.sleep(2000);
            session.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

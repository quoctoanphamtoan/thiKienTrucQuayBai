package toanKienTruc.app;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import toanKienTruc.entity.BenhNhan;

public class ActiveMQConfig {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private Connection connection;

	public ActiveMQConfig() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		try {
			connection = connectionFactory.createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public void Sender(BenhNhan benhNhan) throws JMSException {
		connection.start();	
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Destination destination =session.createQueue("toan");
		MessageProducer messageProducer = session.createProducer(destination);
		
		
		//goi object
		//convert object thanh 1 chuoi json (String)
		
		String objectJson = new Gson().toJson(benhNhan);
		TextMessage textMessage = session.createTextMessage(objectJson);
		messageProducer.send(textMessage);
		connection.close();
	}
	
	
	public MessageConsumer reciver() throws JMSException {
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("toan"); 
		MessageConsumer messageConsumer = session.createConsumer(destination);
		return messageConsumer;
	}
	public static void main(String[] args) throws JMSException {
//		try {
//			new ActiveMQConfig().Sender(new BenhNhan(1,"toan","khoh","hcm","khong bi gi het"));
//		} catch (JMSException e) { 
//			e.printStackTrace();
//		}
		new ActiveMQConfig().reciver().setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				if(message instanceof TextMessage) {
					TextMessage tm = (TextMessage) message;
					try {
						BenhNhan bn = new Gson().fromJson(tm.getText(),BenhNhan.class);
						System.out.println(bn);
					} catch (JsonSyntaxException e) { 
						e.printStackTrace();
					} catch (JMSException e) { 
						e.printStackTrace();
					}
					
				}
			}
		});
		
	}

}

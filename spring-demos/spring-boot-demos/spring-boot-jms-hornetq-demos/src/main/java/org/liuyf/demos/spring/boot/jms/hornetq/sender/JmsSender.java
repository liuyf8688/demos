package org.liuyf.demos.spring.boot.jms.hornetq.sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.liuyf.demos.spring.boot.jms.hornetq.QueueConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void send(final String text) {
		jmsTemplate.send(QueueConfig.DEFAULT_QUEUE, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(text);
			}
		});
	}
}

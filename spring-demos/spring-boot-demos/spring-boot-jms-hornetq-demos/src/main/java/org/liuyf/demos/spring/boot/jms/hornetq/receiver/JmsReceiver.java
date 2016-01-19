package org.liuyf.demos.spring.boot.jms.hornetq.receiver;

import org.liuyf.demos.spring.boot.jms.hornetq.QueueConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {

	@JmsListener(destination = QueueConfig.DEFAULT_QUEUE)
	public void processMessage(String content) {
		System.out.println("receive a message, [ " + content + " ]");
	}
}

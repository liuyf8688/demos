package com.liuyf.demos.spring.boot.amqp.receiver;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.rabbitmq.client.Channel;

@SpringBootApplication
public class SpringBootRabbitMqReceiveApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootRabbitMqReceiveApplication.class, args);
		ConnectionFactory connectionFactory = context.getBean(ConnectionFactory.class);
		
		for (int i = 0; i < 5; i ++) {
			
			RabbitAdmin admin = new RabbitAdmin(connectionFactory);
			Queue queue = admin.declareQueue();
			FanoutExchange exchange = new FanoutExchange("cointiger-exchange");
			admin.declareBinding(BindingBuilder.bind(queue).to(exchange));
			SimpleMessageListenerContainer container = context.getBean(SimpleMessageListenerContainer.class);
			
			container.setQueues(queue);
//			container.setQueueNames("cointiger-queue");
//			Queue queue = new Queue("cointiger-queue", false, true, true);
//			queue.setShouldDeclare(true);
//			container.setQueues(queue);
//			Binding binding = new Binding(, destinationType, exchange, routingKey, arguments)
			container.setAcknowledgeMode(AcknowledgeMode.AUTO);
			container.setMessageListener(new MessageListenerAdapter() {

				@Override
				public void onMessage(Message message, Channel channel) throws Exception {
					
					System.out.println(channel.getChannelNumber() + " ===> " + message.getBody().toString());
				}
				
			});
			
			container.start();
		}
		
	}
}

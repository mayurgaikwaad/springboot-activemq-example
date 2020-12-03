package com.mg.activemq.listener;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Queue queue;

	@JmsListener(destination = "inmemory.activemq.queue", containerFactory = "jmsListenerContainerFactory")
	public void consume(String message) {
		System.out.println("Received Message : " + message);
	}

	@JmsListener(destination = "inmemory.activemq.topic", containerFactory = "jmsTopicListenerContainerFactory")
	public void consumeT1(String message) {
		System.out.println("T1 = " + message);
		jmsTemplate.convertAndSend(queue, message);
	}

	@JmsListener(destination = "inmemory.activemq.topic", containerFactory = "jmsTopicListenerContainerFactory")
	public void consumeT2(String message) {
		System.out.println("T2 = " + message);
	}

}

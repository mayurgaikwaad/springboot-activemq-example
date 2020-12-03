package com.mg.activemq.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@EnableJms
@Configuration
public class JmsConfig {

	//define
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("inmemory.activemq.queue");
	}
	
	@Bean
	public Topic topic() {
		return new ActiveMQTopic("inmemory.activemq.topic");
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
	        DefaultJmsListenerContainerFactoryConfigurer configurer,
	        ConnectionFactory connectionFactory) {

	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    configurer.configure(factory, connectionFactory);
	    return factory;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(
	        DefaultJmsListenerContainerFactoryConfigurer configurer,
	        ConnectionFactory connectionFactory) {

	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    configurer.configure(factory, connectionFactory);
	    factory.setPubSubDomain(true);
	    return factory;
	}
}

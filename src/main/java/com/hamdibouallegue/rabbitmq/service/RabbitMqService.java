package com.hamdibouallegue.rabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamdibouallegue.rabbitmq.config.RabbitMqConfig;

@Service
public class RabbitMqService {
	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * Sends messages via Topic exchange
	 * @param message 
	 */
	public void sendViaTopicExchange(String message) {
		// convertAndSend takes the exchange name, routing-key, and the message
		amqpTemplate.convertAndSend(RabbitMqConfig.topicExchangeName, "demo.test", "this is a test");
	}

	/**
	 * sends messages via Headers exchange
	 * @param message
	 */
	public void sendViaheadersExchange(String message) {
		// creates a message object and set headers
		Message sysErrMsg = MessageBuilder.withBody(message.getBytes()).setHeader("testId", "123")
				.setHeader("anotherId", "another123").build();
		amqpTemplate.convertAndSend(RabbitMqConfig.headerExchangeName, "header.#", sysErrMsg);

	}

}

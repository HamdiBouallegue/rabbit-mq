package com.hamdibouallegue.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	public static final String queueName = "demo-rabbitmq";
	public static final String topicExchangeName = "topic-exchange";
	public static final String headerExchangeName = "demo-headers-exchange";

	/**
	 * We define our queue info
	 * @return Queue object
	 */
	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}

	/**
	 * Creates a topic exchange
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}
	
	/**
	 * Creates a Header Exchange
	 * @return
	 */
	@Bean
	HeadersExchange headers() {
		return new HeadersExchange(headerExchangeName);
	}
	


	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		// bind a queue to a Topic Exchange with routing key
		return BindingBuilder.bind(queue).to(exchange).with("demo.#");
	}
	
	@Bean
	Binding bindingHeader(Queue queue, HeadersExchange exchange) {
		// bind a queue to a Header Exchange with argument testId is exist
		 return BindingBuilder.bind(queue).to(exchange).where("testId").exists();
	}
	
	

}

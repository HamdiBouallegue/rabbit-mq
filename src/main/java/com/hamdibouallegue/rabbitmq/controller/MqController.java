package com.hamdibouallegue.rabbitmq.controller;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamdibouallegue.rabbitmq.service.RabbitMqService;

@RestController
@RequestMapping("/api")
public class MqController {

	@Autowired
	RabbitMqService rabbitMqService;

	@PostMapping(value = "/send")
	public ResponseEntity<String> sendTopic() throws IOException {
		String message = "this is a topic exchange demo";
		rabbitMqService.sendViaTopicExchange(message);
		return ResponseEntity.ok().body("Success");
	}

	@PostMapping(value = "/send-headers")
	public ResponseEntity<String> sendHeaders() throws IOException {
		String message = "this is a headers exchange demo";
		rabbitMqService.sendViaheadersExchange(message);
		return ResponseEntity.ok().body("Success");
	}
	
//	@RabbitListener(queues = "${app.rabbitmq.queue}")
//	/**
//	 * recive messages from rabbitMq queue
//	 * @param incomingMessage
//	 */
//	public void recievedMessage(String incomingMessage) {
//		System.out.println("Recieved Message From RabbitMQ: " + incomingMessage);
//	}
}

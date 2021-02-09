package com.msg.sales.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msg.sales.model.SalesMessage;

@RestController
@RequestMapping("/sales")
public class SalesMessageController {

	private static final Logger log = LoggerFactory.getLogger(SalesMessageController.class);

	private final JmsTemplate jmsTemplate;

	public SalesMessageController(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@PostMapping("/sendMessage")
	public void send(@RequestBody SalesMessage message) {
		log.info("Sending a transaction.");
		jmsTemplate.convertAndSend("OrderTransactionQueue", message);
	}

}

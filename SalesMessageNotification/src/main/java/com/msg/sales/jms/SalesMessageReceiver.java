package com.msg.sales.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.msg.sales.model.SalesMessage;
import com.msg.sales.service.SalesMessageService;



@Component
public class SalesMessageReceiver {

	private static final Logger log = LoggerFactory.getLogger(SalesMessageReceiver.class);

	@Autowired
	SalesMessageService salesMessageService;

	private int count = 0;

	@JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
	public void receiveMessage(SalesMessage message) {
		count++;
		log.info("<" + count + "> Received <" + message + ">");

		salesMessageService.processMessage(message);
		if (count % 10 == 0)
			salesMessageService.printProductlog();
		if (count % 50 == 0)
			salesMessageService.printAdjustmentlog();

	}

}


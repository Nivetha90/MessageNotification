package com.msg.sales.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msg.sales.model.SalesMessage;
import com.msg.sales.repository.SalesMessageRepository;


@Service
public class SalesMessageService {

	private static final Logger log = LoggerFactory.getLogger(SalesMessageService.class);
	
	String messageType3 = "MT3";
	String messageType1 = "MT1";

	@Autowired
	SalesMessageRepository salesMessageRepository;


	public void processMessage(SalesMessage message) {

		if (message.getMessageType().equals(messageType1)) {
			message.setOccurences(1);
		}
		else if (message.getMessageType().equals(messageType3)) {
			List<SalesMessage> previousSales = salesMessageRepository.findByProduct(message.getProduct());
			for (SalesMessage previousSale : previousSales) {
				if (message.getOperation().contentEquals("add")) {
					previousSale.setPrice(previousSale.getPrice() + message.getPrice());

				} else if (message.getOperation().contentEquals("subtract")) {
					previousSale.setPrice(previousSale.getPrice() - message.getPrice());

				} else if (message.getOperation().contentEquals("multiply")) {
					previousSale.setPrice(previousSale.getPrice() * message.getPrice());
				}
				salesMessageRepository.save(previousSale);
			}
		}
		salesMessageRepository.save(message);

	}

	public void printProductlog() {
		List<SalesMessage> salesList = salesMessageRepository.findAll();
		log.info("======================================================================");
		log.info("Product             Price               Occurrences          Total");
		log.info("======================================================================");
		for (SalesMessage sale : salesList) {
			String logStr = String.format("%-20s%-20f%-21d%-20f", sale.getProduct(), sale.getPrice(),
					sale.getOccurences(), (sale.getOccurences() * sale.getPrice()));
			log.info(logStr);

		}
	}

	public void printAdjustmentlog() {
		List<SalesMessage> salesList = salesMessageRepository.findByMessageType(messageType3);
		log.info("==================================================");
		log.info("Product             Price               Operation");
		log.info("==================================================");
		for (SalesMessage sale : salesList) {
			String logStr = String.format("%-20s%-20f%-21s", sale.getProduct(), sale.getPrice(), sale.getOperation());
			log.info(logStr);
		}
	}

}

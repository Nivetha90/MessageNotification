package com.msg.sales.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales_message")
public class SalesMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String messageType;

	private String product;

	private double price;

	private int occurences;

	private String operation;

	public SalesMessage(String messageType, String product, double price) {
		super();
		this.messageType = messageType;
		this.product = product;
		this.price = price;
	}

	public SalesMessage(String messageType, String product, double price, int occurences) {
		super();
		this.messageType = messageType;
		this.product = product;
		this.price = price;
		this.occurences = occurences;
	}

	public SalesMessage(String messageType, String product, double price, String operation) {
		super();
		this.messageType = messageType;
		this.product = product;
		this.price = price;
		this.operation = operation;
	}
	
	

}

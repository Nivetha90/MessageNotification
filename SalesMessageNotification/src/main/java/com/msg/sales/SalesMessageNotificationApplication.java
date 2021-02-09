package com.msg.sales;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;


@SpringBootApplication
public class SalesMessageNotificationApplication {

	private static final Logger log = LoggerFactory.getLogger(SalesMessageNotificationApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SalesMessageNotificationApplication.class, args);
	}
	
	// Only required due to defining myFactory in the receiver
		@Bean
		public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
				DefaultJmsListenerContainerFactoryConfigurer configurer) {
			DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

			// anonymous class
			factory.setErrorHandler(new ErrorHandler() {
				@Override
				public void handleError(Throwable t) {
					log.warn("An error has occurred in the transaction", t);
				}
			});

			// lambda function
			factory.setErrorHandler(t -> log.warn("An error has occurred in the transaction", t));

			configurer.configure(factory, connectionFactory);
			return factory;
		}

		//Message Converter
		@Bean
		public MessageConverter jacksonJmsMessageConverter() {
			MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
			converter.setTargetType(MessageType.TEXT);
			converter.setTypeIdPropertyName("_type");
			return converter;
		}


}

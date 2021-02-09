package com.msg.sales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msg.sales.model.SalesMessage;

@Repository
public interface SalesMessageRepository extends JpaRepository<SalesMessage, Long> {

	public List<SalesMessage> findByProduct(String product);

	public List<SalesMessage> findByMessageType(String messageType);

}

package com.spring.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message,Long>{

	@Query(value="select c from chatLog c")
	public List<Message> getAllMessages();
	
	@Query(value="select c from chatLog c where c.userName=?1")
	public List<Message> getAllMessagesByUserName(String username);
	
	@Query(value="select c from chatLog c where c.messageId=?1")
	public List<Message> findById(int messageId);
	
}

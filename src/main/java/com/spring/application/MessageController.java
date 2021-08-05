package com.spring.application;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatLog")
class MessageController{
	
	@Autowired
	public MessageService messageService;
	
	
	@PostMapping("/{user}")
	public Long addMessage(@PathVariable String user,@RequestBody Message msg)
	{
		msg.setUserName(user);
		return messageService.addMessage(msg);
	}
	
	@GetMapping("/{user}")
	public List<Message> getAllMessagesByUser(@PathVariable String user)
	{
		List<Message> result=messageService.getMessagesByUserName(user);
		Collections.sort(result, Collections.reverseOrder());
		return result;
	}
	
	@DeleteMapping("/{user}")
	public String deleteMessagesByUser(@PathVariable String user) throws Exception
	{
		return messageService.deleteAllMessagesOfUser(user);
	}
	
	@DeleteMapping("/{user}/{id}")
	public String deleteMessagesByUser(@PathVariable("user") String user,@PathVariable("id") Long messageId) throws Exception
	{
		return messageService.deleteSpecificMessagesOfUser(user, messageId);
	}
}
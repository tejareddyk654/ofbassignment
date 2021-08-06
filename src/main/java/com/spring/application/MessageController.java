package com.spring.application;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatlogs")
class MessageController{
	
	@Autowired
	public MessageService messageService;
	
	
	@PostMapping("/{user}")
	public Long addMessage(@PathVariable String user,@RequestBody Message msg)
	{
		System.out.println(msg.getTimeStamp());
		msg.setUserName(user);
		return messageService.addMessage(msg);
	}
	
	@GetMapping("/{user}")
	public List<Message> getAllMessagesByUser(@PathVariable String user,@RequestParam(name="limit",required=false) Integer limit,@RequestParam(name="start",required=false) Integer start)
	{
		List<Message> result=messageService.getMessagesByUserName(user);
		if(limit==null)
			limit=10;
		if(start==null)
			start=0;
		Collections.sort(result, Collections.reverseOrder());
		List<Message> res=new ArrayList<>();
		int count=0;
		for(int i=start;i<result.size();i++) {
			if(count==limit)
				 break;
			res.add(result.get(i));
			count++;
		}
		return res;
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
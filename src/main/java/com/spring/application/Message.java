package com.spring.application;


import javax.persistence.*;

@Entity(name = "chatLog")
@Table(name = "chatLog")
public class Message implements Comparable<Message>{

	@Id
    @GeneratedValue
    private Long messageId;
    @Column(name = "userName", nullable = false, unique = false)
    private String userName;
    private String message;
    private Long timeStamp;
    private Integer isSent;
    
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getIsSent() {
        return isSent;
    }

    public void setIsSent(Integer isSent) {
        this.isSent = isSent;
    }

    public Message(Long messageId, String userName, String message, Long timeStamp, Integer isSent) {
        this.messageId = messageId;
        this.userName = userName;
        this.message = message;
        this.timeStamp = timeStamp;
        this.isSent = isSent;
    }

    
    public Message(String message, Long timeStamp, Integer isSent) {
		super();
		this.message = message;
		this.timeStamp = timeStamp;
		this.isSent = isSent;
	}

	public Message() {
    }
	
	@Override
	public int compareTo(Message o) {
		// TODO Auto-generated method stub
		return this.getTimeStamp().compareTo(o.getTimeStamp());
	}
            
}


package com.spring.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    public Long addMessage(Message message) {
           messageRepository.save(message);
           return message.getMessageId();
    }

    public List<Message> getAllMessages() {
        List<Message> allMessages = messageRepository.getAllMessages();
        return allMessages;
    }

    public List<Message> getMessagesByUserName(String userName) {
        List<Message> messagesOfUser = messageRepository.getAllMessagesByUserName(userName);
        return messagesOfUser;
    }


    @Transactional(rollbackFor = Exception.class)
    public String deleteAllMessagesOfUser(String userName) throws Exception {
        if (userName == null || userName.length() == 0) {
            throw new Exception("Please Enter the user name ");
        }
        List<Message> messagesOfUser = messageRepository.getAllMessagesByUserName(userName);
        if (messagesOfUser.size() == 0) {
            throw new Exception("User with given username not Found!");
        } else {
            messageRepository.deleteAll(messagesOfUser);
        }
        return "deleted all messages of " + userName;
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteSpecificMessagesOfUser(String userName, Long messageId) throws Exception {
        List<Message> messagesOfUser = messageRepository.getAllMessagesByUserName(userName);
        if (messagesOfUser.size() == 0) {
            throw new Exception("user with given username not Found...");
        } else {
            if (messageRepository.findById(messageId) != null) {
                messageRepository.deleteById(messageId);
            } else {
                throw new Exception("user with given messageId not Found....");
            }
        }
        return "deleted specific messages of " + userName;
    }
}

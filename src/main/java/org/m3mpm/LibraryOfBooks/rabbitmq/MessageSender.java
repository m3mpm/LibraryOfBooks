package org.m3mpm.LibraryOfBooks.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.m3mpm.LibraryOfBooks.model.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendBookMessage(String action, Book book){
        try{
            String bookData = objectMapper.writeValueAsString(book);
            String message = action + " , " + bookData;
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, message);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public void sendBookMessage(String action, Long id){
        try{
            String bookData = objectMapper.writeValueAsString(id);
            String message = action + " , " + bookData;
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, message);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public void sendBookMessage(String action, Long id, Book book){
        try{
            String bookData = objectMapper.writeValueAsString(book);
            String message = action + " , " + bookData +  " , " + id.toString();
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, message);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }


}

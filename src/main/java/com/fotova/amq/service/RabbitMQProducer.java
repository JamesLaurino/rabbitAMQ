package com.fotova.amq.service;

import com.fotova.amq.config.RabbitMQConfig;
import com.fotova.amq.dto.ProductDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message)
    {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routing.key.test", message);
        System.out.println("Message envoyé : " + message);
    }

//    public void sendObjet(ProductDto productDto)
//    {
//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routing.key.test", productDto);
//        System.out.println("Message envoyé : " + productDto.toString());
//    }
}

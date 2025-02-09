package com.fotova.amq.controller;

import com.fotova.amq.dto.ProductDto;
import com.fotova.amq.service.RabbitMQProducer;
import org.springframework.web.bind.annotation.*;

@RestController
public class RabbitMQController {
    private final RabbitMQProducer producer;

    public RabbitMQController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String sendObjet(@RequestBody ProductDto productDto) {
        producer.sendMessage(productDto);
        return "Message envoyé : " + productDto.toString();
    }

}

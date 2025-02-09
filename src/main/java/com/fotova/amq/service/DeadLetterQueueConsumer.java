package com.fotova.amq.service;

import com.fotova.amq.config.RabbitMQConfig;
import com.fotova.amq.dto.ProductDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterQueueConsumer
{
    @RabbitListener(queues = RabbitMQConfig.DLQ_NAME)
    public void processFailedMessages(ProductDto productDto)
    {
        System.out.println("📌 Message dans la DLQ : ");
        System.out.println("Product Id : " + productDto.getId());
        System.out.println("Product Name : " + productDto.getName());
        System.out.println("Product Price : " + productDto.getPrice());
        System.out.println("Product Quantity : " + productDto.getQuantity());
    }
}

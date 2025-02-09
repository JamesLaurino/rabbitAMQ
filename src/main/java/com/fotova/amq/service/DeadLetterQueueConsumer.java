package com.fotova.amq.service;

import com.fotova.amq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterQueueConsumer
{
    @RabbitListener(queues = RabbitMQConfig.DLQ_NAME)
    public void processFailedMessages(String message)
    {
        System.out.println("📌 Message dans la DLQ : " + message);
    }
}

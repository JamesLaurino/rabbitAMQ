package com.fotova.amq.service;

import com.fotova.amq.config.RabbitMQConfig;
import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            if(message.equals("error")) {
                throw new RuntimeException("Erreur simulée !");
            }
            System.out.println("Message en MAJ : " + message.toUpperCase());
            channel.basicAck(tag, false); // ✅ Confirmer la bonne réception du message
        } catch (Exception e) {
            System.out.println("🚨 Message rejeté et envoyé à la DLQ !");
            try {
                channel.basicNack(tag, false, false); // ❌ Envoyer le message vers la DLQ
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
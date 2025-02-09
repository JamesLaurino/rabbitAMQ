package com.fotova.amq;

import com.fotova.amq.dto.ProductDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitAmqApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitAmqApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ProductDto productDto = new ProductDto();
        productDto.setId(1);
        productDto.setName("Ford");
        productDto.setPrice(3.6);
        productDto.setQuantity(1);

        System.out.println("✅ Envoi d'un test message à RabbitMQ...");
        rabbitTemplate.convertAndSend("myExchange", "routing.key.test", productDto);
    }
}

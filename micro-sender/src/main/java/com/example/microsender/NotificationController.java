package com.example.microsender;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/notification")
    public void createMessage(@RequestBody Message message){
        System.out.println("Sending message");
        rabbitTemplate.convertAndSend("exchange", "ROUTING_KEY", message.getMessage());
    }

}


package com.example.microsender;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class MicroSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroSenderApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new Queue("queue");
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    @Bean
    public Binding binding(Queue queue , TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("ROUTING_KEY");
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}

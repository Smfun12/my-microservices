package com.example.microcollectior;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CollectionService {

    private static final Logger logger = LogManager.getLogger(CollectionService.class);
    private final MessageCollector messageCollector;
    private MessageRepository messageRepository;

    public CollectionService(MessageCollector messageCollector, MessageRepository messageRepository) {
        this.messageCollector = messageCollector;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/message")
    @Scheduled(fixedRateString = "30000", initialDelayString = "0")
    public void printMessages(){
        logger.info("[INFO]: Get messages from recipient");
        List<String> messages = messageCollector.printMessages();
        logger.info(messages);
        messageRepository.saveAll(messages.stream().map(Message::new).collect(Collectors.toList()));
    }

}

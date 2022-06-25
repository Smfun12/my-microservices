package com.example.microrecipient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RestController
public class Receiver {

    private static final Logger logger = LogManager.getLogger(Receiver.class);
    private final List<String> messageList = new ArrayList<>();

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        logger.info("Received <" + message + ">");
        messageList.add(message);
        latch.countDown();
    }

    @GetMapping("/message")
    public List<String> getMessageList(){
        List<String> messages = new ArrayList<>(messageList);
        messageList.clear();
        logger.info("Show messages");
        return messages;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
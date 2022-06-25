package com.example.microcollectior;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("localhost:8081")
public interface MessageCollector {

    @GetMapping("/message")
    List<String> printMessages();
}

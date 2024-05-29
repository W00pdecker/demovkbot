package com.woopdecker.demoVkBot.service;

import com.woopdecker.demoVkBot.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Service
public class VkService {
    @Value("${vk.access.token}")
    private String accessToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(Message message) {
        String url = "https://api.vk.com/method/messages.send?access_token=" + accessToken + "&v=5.131";

        String jsonBody = String.format("{\"peer_id\": %d, \"message\": \"%s\", \"random_id\": 0}",
                message.getPeer_id(),
                message.getText());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    }
}
package com.woopdecker.demoVkBot.service;

import com.woopdecker.demoVkBot.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class VkService {
    @Value("${vk.access.token}")
    private String accessToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(Message message) {
        String baseUrl = "https://api.vk.com/method/messages.send";
        String answer = "Вы сказали: ";

        // Генерируем случайное значение для random_id
        int randomId = new Random().nextInt(Integer.MAX_VALUE);

        // Создаем параметры запроса
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("peer_id", message.getPeer_id().toString());
        params.put("message", answer + message.getText());
        params.put("random_id", String.valueOf(randomId));
        params.put("v", "5.131");

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (!postData.isEmpty()) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8));
            postData.append('=');
            postData.append(URLEncoder.encode(param.getValue(), StandardCharsets.UTF_8));
        }

        // Создаем заголовки
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Создаем запрос
        HttpEntity<String> request = new HttpEntity<>(postData.toString(), headers);

        // Выполняем запрос и получаем ответ в переменную responce
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);

        //Этот блок использовался при тестировании, закомменчен
        /*if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Сообщение успешно отправлено: " + response.getBody());
        } else {
            System.err.println("Ошибка при отправке сообщения: " + response.getBody());
        }*/
    }
}
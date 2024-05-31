package com.woopdecker.demoVkBot.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woopdecker.demoVkBot.model.Message;
import com.woopdecker.demoVkBot.model.Update;
import com.woopdecker.demoVkBot.service.VkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/callback")
public class VkController {

    @Value("${vk.confirmation.code}")
    private String confirmationCode;

    @Value("${vk.secret}")
    private String secret;

    private final VkService vkService;
    private final ObjectMapper objectMapper;

    public VkController(VkService vkService, ObjectMapper objectMapper) {
        this.vkService = vkService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public String handleUpdate(@RequestBody Update update) { //маппим входящий JSON в класс Update
        //проверяем секретный код
        if (secret != null && !secret.equals(update.getSecret())) {
            return "Invalid secret";
        }

        switch (update.getType()) {
            //подтверждаем адрес сервера
            case "confirmation":
                return confirmationCode;

            case "message_new":
                // Получаем узел vkObject как JsonNode
                JsonNode objectNode = update.getVkObject();
                JsonNode messageNode = objectNode.get("message");
                try {

                    Message message = objectMapper.treeToValue(messageNode, Message.class);
                    System.out.println("Received message: " + message);
                    vkService.sendMessage(message);
                } catch (JsonProcessingException e) {
                    // Ошибка обработки JSON
                    System.err.println("Error processing JSON: " + e.getMessage());
                    e.printStackTrace();
                } catch (Exception e) {
                    // Общая ошибка
                    System.err.println("General error: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + update.getType());
        }

        return "ok";
    }
}
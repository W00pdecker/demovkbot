package com.woopdecker.demoVkBot.controller;

import com.google.gson.Gson;
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

    public VkController(VkService vkService) {
        this.vkService = vkService;
    }

    @PostMapping
    public String handleUpdate(@RequestBody Update update) {
        if (secret != null && !secret.equals(update.getSecret())) {
            return "Invalid secret";
        }

        switch (update.getType()) {
            case "confirmation":
                return confirmationCode;
            case "message_new":
                Message message = new Gson().fromJson(update.getObject().toString(), Message.class);
                vkService.sendMessage(message);
                break;
        }

        return "ok";
    }
}
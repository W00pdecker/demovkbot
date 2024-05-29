package com.woopdecker.demoVkBot.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Message {
    private Long id;
    private Long date;
    private Long peer_id;
    private Long from_id;
    private String text;

    // Getters and Setters
}
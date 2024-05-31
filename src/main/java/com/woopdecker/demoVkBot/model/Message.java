package com.woopdecker.demoVkBot.model;

import lombok.Data;

@Data
public class Message {
    private Long date;
    private Long from_id;
    private Long id;
    //private int out;
    //private int version;
    //private String[] attachments;
    //private Long conversation_message_id;
    //private String[] fwd_messages;
    //private boolean important;
    //private boolean is_hidden;
    private Long peer_id;
    //private Long random_id;
    private String text;
    //private boolean is_mentioned_user;
}
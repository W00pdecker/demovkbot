package com.woopdecker.demoVkBot.model;

import lombok.Data;

@Data
public class Update {
    private String type;
    private Object object;
    private String group_id;
    private String secret;

}
package com.woopdecker.demoVkBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Update {
    private String type;
    @JsonProperty("object")
    private JsonNode vkObject;
    private int group_Id;
    private String secret;
}
package com.woopdecker.demoVkBot.model;

import lombok.Data;

@Data
public class SendMessageResponse {
    private Response response;

    @Data
    public static class Response {
        private Integer message_id;
    }

}
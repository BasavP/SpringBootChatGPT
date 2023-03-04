package com.OpenAISpringBoot.service;

import com.OpenAISpringBoot.config.ChatGptConfig;
import com.OpenAISpringBoot.model.request.BotRequest;
import com.OpenAISpringBoot.model.request.ChatGptRequest;
import com.OpenAISpringBoot.model.response.ChatGptResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BotService {
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequest> buildEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(chatGptRequest, headers);
    }

    //Generate Response
    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL, chatGptRequestHttpEntity, ChatGptResponse.class
        );

        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestion(BotRequest botRequest) {
        return this.getResponse(
                this.buildEntity(
                        new ChatGptRequest(
                                ChatGptConfig.MODEL,
                                botRequest.getMessage(),
                                ChatGptConfig.TEMPERATURE,
                                ChatGptConfig.MAX_TOKEN,
                                ChatGptConfig.TOP_P)));
    }
}
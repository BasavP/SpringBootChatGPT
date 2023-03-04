package com.OpenAISpringBoot.controller;


import com.OpenAISpringBoot.model.request.BotRequest;
import com.OpenAISpringBoot.model.response.ChatGptResponse;
import com.OpenAISpringBoot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class botController {

    private  final BotService botService;

    @PostMapping("/api/v1/bot/send")
    public ChatGptResponse sendMessage(@RequestBody BotRequest botRequest){
            return botService.askQuestion(botRequest);
    }
}

package com.funnelsensai.core.web.request;

import com.funnelsensai.core.dto.conversations.ConversationDto;
import com.funnelsensai.core.service.ConversationGoHighLevelApiServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/auth/api/conversations")
public class ConversationController {

    private final ConversationGoHighLevelApiServices goHighLevelService;

    public ConversationController(ConversationGoHighLevelApiServices goHighLevelService) {
        this.goHighLevelService = goHighLevelService;
    }

    @GetMapping("/get")
    public ConversationDto getConversations() throws IOException {
        return goHighLevelService.fetchConversation();
    }

    @GetMapping("/save")
    public ConversationDto saveConversation() throws IOException {
        try {return goHighLevelService.saveConversation();}
        catch (Exception e) {e.printStackTrace();

    }
        System.out.println("you're trying to save a conversation object doesn't exist yet, call the api first!");
        return null;
    }}

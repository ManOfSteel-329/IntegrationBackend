package com.funnelsensai.core.web.request;

import com.funnelsensai.core.dto.conversations.ConversationDto;
import com.funnelsensai.core.service.ConversationGoHighLevelApiServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class ConversationController {

    private final ConversationGoHighLevelApiServices goHighLevelService;

    public ConversationController(ConversationGoHighLevelApiServices goHighLevelService) {
        this.goHighLevelService = goHighLevelService;
    }

    @GetMapping("/conversations")
    public ConversationDto getConversations() throws IOException {
        return goHighLevelService.fetchConversation();
    }

    @GetMapping("/conversations/save")
    public ConversationDto saveConversation() throws IOException {
        return goHighLevelService.saveConversation();
    }
}

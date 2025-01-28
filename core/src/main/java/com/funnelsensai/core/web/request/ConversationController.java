package com.funnelsensai.core.web.request;

import com.funnelsensai.core.dto.conversations.ConversationDto;
import com.funnelsensai.core.service.ConversationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth/api/conversations")
public class ConversationController {

    private final ConversationService goHighLevelService;

    public ConversationController(ConversationService goHighLevelService) {
        this.goHighLevelService = goHighLevelService;
    }

    @GetMapping("/get")
    public ConversationDto getConversations() throws IOException {
        return goHighLevelService.fetchConversation();
    }
}

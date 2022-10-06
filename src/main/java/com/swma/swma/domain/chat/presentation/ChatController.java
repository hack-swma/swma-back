package com.swma.swma.domain.chat.presentation;

import com.swma.swma.domain.chat.presentation.dto.response.ChatListResponse;
import com.swma.swma.domain.chat.service.ChatListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatListService chatListService;
    @GetMapping("/chat/chat-list")
    @ResponseBody
    public ResponseEntity<List<ChatListResponse>> getChatList(){
        List<ChatListResponse> chatListResponses = chatListService.execute();
        return new ResponseEntity<>(chatListResponses, HttpStatus.OK);
    }
}

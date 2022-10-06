package com.swma.swma.domain.chat.presentation;

import com.swma.swma.domain.chat.presentation.dto.request.ChatRoomRequest;
import com.swma.swma.domain.chat.presentation.dto.response.ChatListResponse;
import com.swma.swma.domain.chat.presentation.dto.response.ConversationResponse;
import com.swma.swma.domain.chat.service.ChatListService;
import com.swma.swma.domain.chat.service.ConversationListService;
import com.swma.swma.domain.chat.service.CreateChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatController {
    private final ChatListService chatListService;
    private final CreateChatRoomService createChatRoomService;
    private final ConversationListService conversationListService;
    @PostMapping("/chat-list")
    public ResponseEntity<List<ChatListResponse>> getChatList(@RequestBody ChatRoomRequest chatRoomRequest){
        createChatRoomService.execute(chatRoomRequest);
        List<ChatListResponse> chatListResponses = chatListService.execute();
        return new ResponseEntity<>(chatListResponses, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ConversationResponse>> getConversationList(@RequestBody ChatRoomRequest chatRoomRequest){
        List<ConversationResponse> conversationListServiceList = conversationListService.execute(chatRoomRequest);
        return new ResponseEntity<>(conversationListServiceList,HttpStatus.OK);
    }
}

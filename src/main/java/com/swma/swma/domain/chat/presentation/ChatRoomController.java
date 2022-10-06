package com.swma.swma.domain.chat.presentation;

import com.swma.swma.domain.chat.entity.Chat;
import com.swma.swma.domain.chat.presentation.dto.request.ChatRoomRequest;
import com.swma.swma.domain.chat.presentation.dto.request.SearchConversationRequest;
import com.swma.swma.domain.chat.presentation.dto.response.ChatListResponse;
import com.swma.swma.domain.chat.presentation.dto.response.ConversationResponse;
import com.swma.swma.domain.chat.service.ChatListService;
import com.swma.swma.domain.chat.service.ConversationListService;
import com.swma.swma.domain.chat.service.CreateChatRoomService;
import com.swma.swma.domain.chat.service.SearchConversationListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatRoomController {
    private final ChatListService chatListService;
    private final CreateChatRoomService createChatRoomService;
    private final ConversationListService conversationListService;
    private final SearchConversationListService searchConversationListService;
    @PostMapping("/list")
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
    @GetMapping("/search")
    public ResponseEntity<List<ChatListResponse>> searchConversationList(@RequestParam SearchConversationRequest searchConversationRequest){
        List<ChatListResponse> chatListResponses = searchConversationListService.execute(searchConversationRequest.getName());
        return new ResponseEntity<>(chatListResponses,HttpStatus.OK);
    }
}

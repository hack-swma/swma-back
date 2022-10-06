package com.swma.swma.domain.chat.service;

import com.swma.swma.domain.chat.entity.Chat;
import com.swma.swma.domain.chat.presentation.dto.request.ChatRoomRequest;
import com.swma.swma.domain.chat.presentation.dto.response.ConversationResponse;
import com.swma.swma.domain.chat.repository.ChatRepository;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationListService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ConversationResponse> execute(ChatRoomRequest chatRoomRequest){
        List<Chat> chatList = chatRepository.findChatByChatRoomId(chatRoomRequest.getId());
        return getChatData(chatList);
    }
    private List<ConversationResponse> getChatData(List<Chat>chatList){
        List<ConversationResponse> list = new ArrayList<>();
        chatList.forEach(chat ->{
            User user = userRepository.findUserById(chat.getFromUserId()).orElseThrow(()-> UserNotFoundException.EXCEPTION);
            list.add(ConversationResponse.builder()
                    .userImg(user.getImg())
                    .userName(user.getName())
                    .message(chat.getMessage())
                    .build());
        });
        return list;
    }
}

package com.swma.swma.domain.chat.service;

import com.swma.swma.domain.chat.entity.ChatRoom;
import com.swma.swma.domain.chat.presentation.dto.response.ChatListResponse;
import com.swma.swma.domain.chat.repository.ChatRoomRepository;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.UserUtils;
import com.swma.swma.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatListService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserUtils userUtils;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ChatListResponse> execute(){
        User user = userUtils.currentUser();
        List<ChatRoom> chatRoomList = chatRoomRepository.findAllByFromUserOrderByLatestDateDesc(user);
        return getChatRoomUser(chatRoomList);
    }
    private List<ChatListResponse> getChatRoomUser(List<ChatRoom> chatRoomList){
        List<ChatListResponse> chatListResponses  = new ArrayList<>();
        chatRoomList.forEach(chatRoom -> {
            User user = userRepository.findById(chatRoom.getToUserId()).orElseThrow(()-> UserNotFoundException.EXCEPTION);
            chatListResponses.add(ChatListResponse.builder()
                    .userImg(user.getImg())
                    .userName(user.getName())
                    .latestDate(chatRoom.getLatestDate())
                    .certifyDate(user.getCertifyDate())
                    .build());
        });
        return chatListResponses;
    }
}

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchConversationListService {
    private final UserUtils userUtils;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public List<ChatListResponse> execute(String name){
        User user = userUtils.currentUser();
        List<ChatRoom> chatRoomList = chatRoomRepository.findAllByFromUserOrderByLatestDateDesc(user);
        return searchChatList(chatRoomList,name);
    }
    private List<ChatListResponse> searchChatList(List<ChatRoom> chatRoomList,String name){
        List<ChatListResponse> chatListResponses  = new ArrayList<>();
        List<User> userList = userRepository.findUserByNameContaining(name);
        chatRoomList.stream().filter(chatRoom -> userList.stream().filter(user -> user.getId()==chatRoom.getToUserId()).isParallel()).map(chatRoom -> {
            User user = userRepository.findById(chatRoom.getToUserId()).orElseThrow(()-> UserNotFoundException.EXCEPTION);
            LocalDate nowDate = LocalDate.now();
            int certifyDate;
            if(nowDate.getYear()-user.getDate().getYear()!=0) {
                certifyDate = (365*(nowDate.getYear()-user.getDate().getYear()))+user.getDate().getDayOfYear();
            } else {
                certifyDate = nowDate.getDayOfYear()-user.getDate().getDayOfYear();
            }
            chatListResponses.add(ChatListResponse.builder()
                    .userImg(user.getImg())
                    .userName(user.getName())
                    .latestDate(chatRoom.getLatestDate())
                    .certifyDate(certifyDate)
                    .build());;
            return chatListResponses;
        });
        return chatListResponses;
    }
}

package com.swma.swma.domain.chat.service;

import com.swma.swma.domain.chat.entity.ChatRoom;
import com.swma.swma.domain.chat.presentation.dto.request.ChatRoomRequest;
import com.swma.swma.domain.chat.repository.ChatRoomRepository;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.UserUtils;
import com.swma.swma.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserUtils userUtils;
    private final UserRepository userRepository;

    @Transactional
    public void execute(ChatRoomRequest chatRoomRequest){
        User user = userUtils.currentUser();
        if(userRepository.existsById(chatRoomRequest.getId())) throw new UserNotFoundException();
        chatRoomRepository.save(ChatRoom.builder()
                .fromUser(user)
                .toUserId(chatRoomRequest.getId())
                .build());
    }
}

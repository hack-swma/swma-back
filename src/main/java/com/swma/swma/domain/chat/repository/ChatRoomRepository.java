package com.swma.swma.domain.chat.repository;

import com.swma.swma.domain.chat.entity.ChatRoom;
import com.swma.swma.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRoomRepository extends CrudRepository<ChatRoom,Long> {
    List<ChatRoom> findAllByFromUserOrderByLatestDateDesc(User user);
    List<ChatRoom> findByToUserIdContaining(Long toUserId);
}

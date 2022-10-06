package com.swma.swma.domain.chat.repository;

import com.swma.swma.domain.chat.entity.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat,Long> {
    List<Chat> findChatByChatRoomId(Long chatRoomId);
}

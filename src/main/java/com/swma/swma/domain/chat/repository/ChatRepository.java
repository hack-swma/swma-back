package com.swma.swma.domain.chat.repository;

import com.swma.swma.domain.chat.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat,Long> {
}

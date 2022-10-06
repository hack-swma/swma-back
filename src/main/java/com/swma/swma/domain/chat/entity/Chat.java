package com.swma.swma.domain.chat.entity;

import lombok.*;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatRoomId;

    private Long fromUserId;

    private String message;

    @Builder
    public Chat(Long chatRoomId, Long fromUserId, String message) {
        this.chatRoomId = chatRoomId;
        this.fromUserId = fromUserId;
        this.message = message;
    }

}

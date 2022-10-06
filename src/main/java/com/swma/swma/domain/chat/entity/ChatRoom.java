package com.swma.swma.domain.chat.entity;

import com.swma.swma.domain.user.entity.User;
import lombok.*;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long toUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User fromUser;
    private LocalDateTime latestDate;
}

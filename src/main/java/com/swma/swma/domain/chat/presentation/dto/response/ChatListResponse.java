package com.swma.swma.domain.chat.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class ChatListResponse {
    private final String userImg;
    private final String userName;
    private final LocalDateTime latestDate;
    private final int certifyDate;
}

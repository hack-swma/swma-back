package com.swma.swma.domain.chat.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ConversationResponse {
    private final String userImg;
    private final String userName;
    private final String message;
}

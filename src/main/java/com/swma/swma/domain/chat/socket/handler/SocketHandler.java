package com.swma.swma.domain.chat.socket.handler;

import com.swma.swma.domain.chat.entity.Chat;
import com.swma.swma.domain.chat.repository.ChatRepository;
import com.swma.swma.domain.chat.repository.ChatRoomRepository;
import com.swma.swma.domain.user.entity.User;
import com.swma.swma.domain.user.repository.UserRepository;
import com.swma.swma.global.UserUtils;
import com.swma.swma.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketHandler extends TextWebSocketHandler {
    private static List<WebSocketSession> list = new ArrayList<>();
    private final ChatRepository chatRepository;
    private final UserUtils userUtils;

    /* 텍스트 메시지 메서드 */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage reqMessage) throws Exception {
        String msg = reqMessage.getPayload();
        User userData = userUtils.currentUser();
        JSONObject obj = jsonToObjectParser(msg);
        Long rN = (Long) obj.get("id");
        for(WebSocketSession sess: list) {
            /*chatRepository.save(Chat.builder()
                    .chatRoomId(rN)
                    .fromUserId(userData.getId())
                    .message(String.valueOf(reqMessage))
                    .build());*/
            sess.sendMessage(reqMessage);
        }
    }
    /* 클라이언트가 접속 요청 시 호출*/
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        list.add(session);
    }

    /* 클라이언트가 접속 해제 시 호출 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        list.remove(session);
    }
    private static JSONObject jsonToObjectParser(String jsonStr){
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}

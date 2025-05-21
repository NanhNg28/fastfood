package com.nanhng.FastFood.other_service.web_socket;

import com.nanhng.FastFood.dto.response.order.AddOrderRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void notifyNewOrder(AddOrderRes event){
        log.info("New order {} received", event.getId());
        ChatMessage message = ChatMessage.builder()
                .sender("system")
                .content("New order received: order #" + event.getId())
                .build();
        messagingTemplate.convertAndSend("/private/employee", message);
    }
}

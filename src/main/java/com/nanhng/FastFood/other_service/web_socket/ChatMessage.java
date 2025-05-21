package com.nanhng.FastFood.other_service.web_socket;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    String content;
    String sender;
}

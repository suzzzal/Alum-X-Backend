package com.opencode.alumxbackend.groupchatmessages.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class GroupMessageResponse {
    private UUID id;
    private String senderUserId;
    private String senderUsername;
    private String content;
    private Instant createdAt;
}

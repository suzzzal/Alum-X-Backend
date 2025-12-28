package com.opencode.alumxbackend.groupchatmessages.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SendGroupMessageRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Message content cannot be empty")
    @Size(max = 1000, message = "Message must be at most 1000 characters")
    private String content;
}

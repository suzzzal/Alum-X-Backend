package com.opencode.alumxbackend.groupchatmessages.service;

import com.opencode.alumxbackend.groupchatmessages.dto.GroupMessageResponse;
import com.opencode.alumxbackend.groupchatmessages.dto.SendGroupMessageRequest;

import java.util.List;

public interface GroupMessageService {
    GroupMessageResponse sendMessage(
            String groupId,
            SendGroupMessageRequest request
    );

    List<GroupMessageResponse> fetchMessages(
            String groupId,
            String userId
    );
}

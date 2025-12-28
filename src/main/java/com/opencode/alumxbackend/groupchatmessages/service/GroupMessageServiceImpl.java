package com.opencode.alumxbackend.groupchatmessages.service;

import com.opencode.alumxbackend.groupchat.model.GroupChat;
import com.opencode.alumxbackend.groupchatmessages.dto.GroupMessageResponse;
import com.opencode.alumxbackend.groupchatmessages.dto.SendGroupMessageRequest;
import com.opencode.alumxbackend.groupchatmessages.exception.GroupNotFoundException;
import com.opencode.alumxbackend.groupchatmessages.exception.InvalidMessageException;
import com.opencode.alumxbackend.groupchatmessages.exception.UserNotMemberException;
import com.opencode.alumxbackend.groupchatmessages.model.GroupMessage;
import com.opencode.alumxbackend.groupchatmessages.repository.GroupMessageRepository;
import com.opencode.alumxbackend.groupchat.repository.GroupChatRepository;
import com.opencode.alumxbackend.groupchat.model.GroupChat.Participant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMessageServiceImpl implements GroupMessageService {

    private final GroupMessageRepository messageRepository;
    private final GroupChatRepository groupChatRepository;

    @Override
    public GroupMessageResponse sendMessage(
            String groupId,
            SendGroupMessageRequest request
    ) {

        GroupChat group = groupChatRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));

        boolean isMember = group.getParticipants()
                .stream()
                .anyMatch(p -> p.getUserId().equals(request.getUserId()));

        if (!isMember) {
            throw new UserNotMemberException(request.getUserId());
        }

        if (request.getContent().isEmpty()) {
            throw new InvalidMessageException("Message cannot be empty");
        }

        Participant sender = group.getParticipants().stream()
                .filter(p -> p.getUserId().equals(request.getUserId()))
                .findFirst()
                .orElseThrow();
        GroupMessage message = GroupMessage.builder()
                .groupId(groupId)
                .senderUserId(sender.getUserId())
                .senderUsername(sender.getUsername())
                .content(request.getContent())
                .createdAt(Instant.now())
                .build();

        messageRepository.save(message);

        return mapToResponse(message);
    }

    @Override
    public List<GroupMessageResponse> fetchMessages(
            String groupId,
            String userId
    ) {

        GroupChat group = groupChatRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        boolean isMember = group.getParticipants()
                .stream()
                .anyMatch(p -> p.getUserId().equals(userId));

        if (!isMember) {
            throw new RuntimeException("Access denied");
        }

        return messageRepository.findByGroupIdOrderByCreatedAtAsc(groupId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private GroupMessageResponse mapToResponse(GroupMessage message) {
        return GroupMessageResponse.builder()
                .id(message.getId())
                .senderUserId(message.getSenderUserId())
                .senderUsername(message.getSenderUsername())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build();
    }
}

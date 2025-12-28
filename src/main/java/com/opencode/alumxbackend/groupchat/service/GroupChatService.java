package com.opencode.alumxbackend.groupchat.service;

import com.opencode.alumxbackend.groupchat.dto.GroupChatRequest;
import com.opencode.alumxbackend.groupchat.model.GroupChat;
import com.opencode.alumxbackend.groupchat.model.GroupChat.Participant;
import com.opencode.alumxbackend.groupchat.repository.GroupChatRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupChatService {
    private final GroupChatRepository repository;

    public GroupChat createGroup(GroupChatRequest request){
        // Remove duplicate participants
        List<Participant> participants = request.getParticipants().stream()
                .distinct()
                .map(p -> new GroupChat.Participant(p.getUserId(), p.getUsername()))
                .collect(Collectors.toList());

        String groupId = UUID.randomUUID().toString();

        GroupChat group = GroupChat.builder()
                .groupId(groupId)
                .name(request.getName())
                .participants(participants)
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(group);
    }

    public GroupChat getGroupById(String groupId) {
        return repository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public List<GroupChat> getGroupsForUser(String userId) {
        return repository.findGroupsByUserId(userId);
    }
}

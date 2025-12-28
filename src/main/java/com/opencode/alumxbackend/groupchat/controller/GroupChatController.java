package com.opencode.alumxbackend.groupchat.controller;

import com.opencode.alumxbackend.groupchat.dto.GroupChatRequest;
import com.opencode.alumxbackend.groupchat.model.GroupChat;
import com.opencode.alumxbackend.groupchat.service.GroupChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/group-chats")
@RequiredArgsConstructor
public class GroupChatController {

    private final GroupChatService service;
    // Create a group
    @PostMapping
    public ResponseEntity<GroupChat> createGroup(@Valid @RequestBody GroupChatRequest request){
        GroupChat group = service.createGroup(request);
        return ResponseEntity.ok(group);
    }

    // Get group by groupId
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupChat> getGroupById(
            @PathVariable String groupId
    ) {
        return ResponseEntity.ok(service.getGroupById(groupId.trim()));
    }

    // Get all groups for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getGroupsForUser(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(service.getGroupsForUser(userId));
    }
}

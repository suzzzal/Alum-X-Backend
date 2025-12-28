package com.opencode.alumxbackend.groupchatmessages.controller;

import com.opencode.alumxbackend.groupchatmessages.dto.GroupMessageResponse;
import com.opencode.alumxbackend.groupchatmessages.dto.SendGroupMessageRequest;
import com.opencode.alumxbackend.groupchatmessages.service.GroupMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupMessageController {

    private final GroupMessageService service;

    @PostMapping(value="/{groupId}/messages",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMessage(
            @PathVariable String groupId,
            @RequestBody @Valid SendGroupMessageRequest request
    ) {
        return ResponseEntity.ok(service.sendMessage(groupId, request));
    }

    @GetMapping("/{groupId}/messages")
    public List<GroupMessageResponse> getMessages(
            @PathVariable String groupId,
            @RequestParam String userId
    ) {
        return service.fetchMessages(groupId, userId);
    }
}

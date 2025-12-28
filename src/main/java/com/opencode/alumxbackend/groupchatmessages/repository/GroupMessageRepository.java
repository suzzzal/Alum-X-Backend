package com.opencode.alumxbackend.groupchatmessages.repository;

import com.opencode.alumxbackend.groupchatmessages.model.GroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GroupMessageRepository extends JpaRepository<GroupMessage, UUID> {
    List<GroupMessage> findByGroupIdOrderByCreatedAtAsc(String groupId);
}

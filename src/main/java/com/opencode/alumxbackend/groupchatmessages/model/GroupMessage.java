package com.opencode.alumxbackend.groupchatmessages.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "group_messages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessage {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String groupId;

    @Column(nullable = false)
    private String senderUserId;

    @Column(nullable = false)
    private String senderUsername;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private Instant createdAt;
}

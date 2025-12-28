package com.opencode.alumxbackend.groupchat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_chats")
public class GroupChat {
    @Id
    private String groupId;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "group_chat_participants",
            joinColumns = @JoinColumn(name = "group_id")
    )
    private List<Participant> participants;

    private LocalDateTime createdAt;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Participant {
        private String userId;
        private String username;
    }
}

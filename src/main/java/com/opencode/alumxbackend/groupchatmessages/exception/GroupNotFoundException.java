package com.opencode.alumxbackend.groupchatmessages.exception;


public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(String groupId) {
        super("Group not found: " + groupId);
    }
}

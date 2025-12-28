package com.opencode.alumxbackend.groupchatmessages.exception;

public class UserNotMemberException extends RuntimeException {
    public UserNotMemberException(String userId) {
        super("User is not a member of this group: " + userId);
    }
}


package com.opencode.alumxbackend.groupchatmessages.exception;

public class InvalidMessageException extends RuntimeException {
    public InvalidMessageException(String msg) {
        super(msg);
    }
}
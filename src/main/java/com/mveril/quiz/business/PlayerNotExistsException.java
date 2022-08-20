package com.mveril.quiz.business;

public class PlayerNotExistsException extends Exception {
    public PlayerNotExistsException(long questionId) {
        super(String.format("The player with id: %d not exists",questionId));
    }
}

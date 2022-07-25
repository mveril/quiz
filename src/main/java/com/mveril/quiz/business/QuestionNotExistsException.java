package com.mveril.quiz.business;

public class QuestionNotExistsException extends Throwable {
    public QuestionNotExistsException(long questionId) {
        super(String.format("The question with id: %d not exists",questionId));
    }
}

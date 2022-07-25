package com.mveril.quiz.business;

public class AnswerNotMatchQuestionException extends Exception{
    public AnswerNotMatchQuestionException(long questionId,long answerId){
        super(String.format("The answer with id %d not match the question with id %d",answerId,questionId));
    }
}

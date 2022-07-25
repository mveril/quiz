package com.mveril.quiz.business.service;

import com.mveril.quiz.business.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    QuestionStoreService store;

    public Question GetNextQuestion(){
        return store.getRandomQuestion();
    }

    public boolean validateAnswer(Question question, long answerId) throws AnswerNotMatchQuestionException {
        for (Answer answer: question.getAnswers()) {
            if (answer.getId() == answerId){
                return  answer.isCorrectAnswer();
            }
        }
        throw new AnswerNotMatchQuestionException(question.getId(),answerId);
    }

    public boolean validateAnswer(long questionId,long answerId) throws AnswerNotMatchQuestionException, QuestionNotExistsException {
        var question = store.getQuestionById(questionId);
        if (question.isPresent()){
            return validateAnswer(question.get(),answerId);
        }
        throw new QuestionNotExistsException(questionId);
    }
}

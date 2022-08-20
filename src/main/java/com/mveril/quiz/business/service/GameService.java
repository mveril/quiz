package com.mveril.quiz.business.service;

import com.mveril.quiz.DTO.GameQuestionDTO;
import com.mveril.quiz.business.*;
import com.mveril.quiz.business.service.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    QuestionStoreService questionStore;

    @Autowired
    PlayerStoreService playerStore;

    @Autowired
    QuestionMapper questionMapper;

    public GameQuestionDTO GetNextQuestion(){
        return questionMapper.toGameQuestion(questionStore.getRandomQuestion());
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
        var question = questionStore.getQuestionById(questionId);
        if (question.isPresent()){
            return validateAnswer(question.get(),answerId);
        }
        throw new QuestionNotExistsException(questionId);
    }

    public boolean validateAnswer(long questionId,long answerId,long playerId) throws AnswerNotMatchQuestionException, QuestionNotExistsException, PlayerNotExistsException {
        var question = questionStore.getQuestionById(questionId);
        if (question.isPresent()){
            playerStore.incrementScore(playerId);
            return validateAnswer(question.get(),answerId);
        }
        throw new QuestionNotExistsException(questionId);
    }
}

package com.mveril.quiz.business.service.mapper;

import com.mveril.quiz.DTO.GameAnswerDTO;
import com.mveril.quiz.business.Answer;
import org.springframework.stereotype.Service;

@Service
public class AnswerMapper {
    public GameAnswerDTO toGameAnswer(Answer answer){
        var gameAnswer = new GameAnswerDTO();
        gameAnswer.setId(answer.getId());
        gameAnswer.setTitle(answer.getTitle());
        return gameAnswer;
    }
}

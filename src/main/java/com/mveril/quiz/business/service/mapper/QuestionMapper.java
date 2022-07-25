package com.mveril.quiz.business.service.mapper;

import com.mveril.quiz.DTO.GameQuestionDTO;
import com.mveril.quiz.business.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionMapper {
    @Autowired
    AnswerMapper answerMapper;

    public GameQuestionDTO toGameQuestion(Question question){
        var gameQuestion = new GameQuestionDTO();
        gameQuestion.setId(question.getId());
        gameQuestion.setAnswers(question.getAnswers().stream().map((a)->answerMapper.toGameAnswer(a)).toList());
        gameQuestion.setTitle(question.getTitle());
        return gameQuestion;
    }
}

package com.mveril.quiz.Controller;

import com.mveril.quiz.business.AnswerNotMatchQuestionException;
import com.mveril.quiz.business.Question;
import com.mveril.quiz.business.QuestionNotExistsException;
import com.mveril.quiz.business.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/play")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("question")
    public Question GetNextQuestion(){
        return gameService.GetNextQuestion();
    }

    @GetMapping("answer/{qid}/{aid}")
    public ResponseEntity<Boolean> answer(long qid, long aid){
        try {
            return ResponseEntity.ok(gameService.validateAnswer(qid,aid));
        } catch (AnswerNotMatchQuestionException e) {
            return  ResponseEntity.badRequest().build();
        } catch (QuestionNotExistsException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.mveril.quiz.Controller;

import com.mveril.quiz.DTO.GameQuestionDTO;
import com.mveril.quiz.business.AnswerNotMatchQuestionException;
import com.mveril.quiz.business.Question;
import com.mveril.quiz.business.QuestionNotExistsException;
import com.mveril.quiz.business.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/play")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("question")
    public GameQuestionDTO GetNextQuestion(){
        return gameService.GetNextQuestion();
    }

    @GetMapping("answer/{qid}/{aid}")
    public ResponseEntity<Boolean> answer(@PathVariable("qid") long qid, @PathVariable("aid")  long aid){
        try {
            return ResponseEntity.ok(gameService.validateAnswer(qid,aid));
        } catch (AnswerNotMatchQuestionException e) {
            return  ResponseEntity.badRequest().build();
        } catch (QuestionNotExistsException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

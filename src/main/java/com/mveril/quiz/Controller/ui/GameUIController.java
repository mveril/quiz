package com.mveril.quiz.Controller.ui;

import com.mveril.quiz.business.AnswerNotMatchQuestionException;
import com.mveril.quiz.business.QuestionNotExistsException;
import com.mveril.quiz.business.service.GameService;
import com.mveril.quiz.business.service.QuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("play")
public class GameUIController {
    @Autowired
    GameService gameService;
    @Autowired
    QuestionStoreService questionStoreService;

    @GetMapping
    public String NextQuestion(Model model){
        model.addAttribute("question", gameService.GetNextQuestion());
        model.addAttribute("showResults",false);
        return "question.html";
    }
    @PostMapping
    public String answerQuestion(Model model,  @RequestParam(name="question") long qid, @RequestParam(name = "answer") long aid){
        boolean showResults = false;
        try {
            var goodAnswer = gameService.validateAnswer(qid,aid);
            showResults = goodAnswer;
            model.addAttribute("isGoodResult", goodAnswer);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("question",questionStoreService.getQuestionById(qid).get());
        model.addAttribute("showResults",showResults);
        return "question.html";
    }
}

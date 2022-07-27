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
        return "question.html";
    }
    @PostMapping
    public String answerQuestion(Model model,  @RequestParam Map<String, String> responseMap){
        var qidSTR = responseMap.get("question");
        var qid =Long.parseLong(qidSTR);
        var aidSTR = responseMap.get("answer");
        var aid =Long.parseLong(aidSTR);
        try {
            model.addAttribute("isGoodResult",gameService.validateAnswer(qid,aid));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("question",questionStoreService.getQuestionById(qid).get());
        return "question.html";
    }
}

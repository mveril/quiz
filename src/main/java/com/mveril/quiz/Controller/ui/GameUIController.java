package com.mveril.quiz.Controller.ui;

import com.mveril.quiz.business.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("play")
public class GameUIController {
    @Autowired
    GameService gameService;

    @GetMapping
    public String GetNextQuestion(Model model){
        model.addAttribute("question", gameService.GetNextQuestion());
        return "question.html";
    }
}

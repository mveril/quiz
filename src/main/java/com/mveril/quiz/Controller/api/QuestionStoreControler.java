package com.mveril.quiz.Controller.api;

import com.mveril.quiz.business.Question;
import com.mveril.quiz.business.service.QuestionStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/store/questions")
public class QuestionStoreControler {

    @Autowired
    QuestionStoreService questionStore;

    @GetMapping()
    public List<Question> list(){
        return questionStore.getQuestions();
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathParam("id") long questionId){
        if(questionStore.removeQuestion(questionId)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public void add(@RequestBody Question question){
        questionStore.add(question);
    }

    @GetMapping("{id}")
    public ResponseEntity<Question> get(@PathParam("id") long id){
        var o = questionStore.getQuestionById(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathParam("id") long id, @RequestBody Question question){
        if(question.getId() != id){
            return ResponseEntity.badRequest().build();
        }
        if(questionStore.update(question)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

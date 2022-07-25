package com.mveril.quiz.Controller;

import com.mveril.quiz.business.Question;
import com.mveril.quiz.business.service.QuestionStoreService;
import com.mveril.quiz.business.service.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/store")
public class StoreControler {

    @Autowired
    QuestionStoreService questionStore;

    @GetMapping("questions")
    public List<Question> list(){
        return questionStore.getQuestions();
    }

    @DeleteMapping("questions/{id}")
    public ResponseEntity remove(@PathParam("id") long questionId){
        if(questionStore.removeQuestion(questionId)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("questions")
    public void add(@RequestBody Question question){
        questionStore.add(question);
    }

    @GetMapping("questions/{id}")
    public ResponseEntity<Question> get(@PathParam("id") long id){
        var o = questionStore.getQuestionById(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("questions/{id}")
    public ResponseEntity get(@PathParam("id") long id, @RequestBody Question question){
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

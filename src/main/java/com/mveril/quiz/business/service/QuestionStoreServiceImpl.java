package com.mveril.quiz.business.service;

import com.mveril.quiz.business.*;
import com.mveril.quiz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class QuestionStoreServiceImpl implements QuestionStoreService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    Random rd= new Random();

    @Override
    public void add(Question question) {
        for (Answer answer : question.getAnswers() ){
            answer.setQuestion(question);
        }
        questionRepository.save(question);
    }

    public boolean removeQuestion(Question question) {
        return removeQuestion(question.getId());
    }

    public boolean removeQuestion(long id) {
        var result = questionRepository.existsById(id);
        if(result){
            questionRepository.deleteById(id);
        }
        return result;
    }

    @Override
    public Question getRandomQuestion() {
        var questions = questionRepository.findAll();
        return questions.get(rd.nextInt(questions.size()));
    }

    @Override
    public Optional<Question> getQuestionById(long id) {
        return questionRepository.findById(id);
    }

    @Override
    public boolean update(Question question) {
        var result = questionRepository.existsById(question.getId());
        if (result){
            for (Answer answer:
                 question.getAnswers()) {
                answer.setQuestion(question);
            }
            questionRepository.save(question);
        }
        return result;
    }


    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }
}

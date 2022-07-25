package com.mveril.quiz.business.service;

import com.mveril.quiz.business.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QuestionStoreService {
    void add(Question question);
    List<Question> getQuestions();

    boolean removeQuestion(Question question);

    boolean removeQuestion(long questionId);

    Question getRandomQuestion();

    Optional<Question> getQuestionById(long id);

    boolean update(Question question);
}

package com.mveril.quiz.DTO;

import com.mveril.quiz.business.Answer;

import java.util.ArrayList;
import java.util.List;

public class GameQuestionDTO {
    private long id;
    private String title;

    private List<GameAnswerDTO> answers = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GameAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<GameAnswerDTO> answers) {
        this.answers = answers;
    }
}

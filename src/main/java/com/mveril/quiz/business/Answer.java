package com.mveril.quiz.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mveril.quiz.business.Question;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column(name="correct_answer")
    private boolean correctAnswer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    public Answer() {
    }

    public Answer(String title, boolean correctAnswer) {
        this.title = title;
        this.correctAnswer = correctAnswer;
    }


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

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" + "id=" + id + ", title=" + title + ", correctAnswer=" + correctAnswer + '}';
    }
}

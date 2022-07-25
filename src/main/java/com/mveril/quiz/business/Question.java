package com.mveril.quiz.business;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers = new ArrayList<>();


    public Question() {
    }

    public Question(String title) {
        this.title = title;
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

    public Collection<Answer> getAnswers() {
        return Collections.unmodifiableCollection(answers);
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", title=" + title + ", answers=" + answers + '}';
    }

}

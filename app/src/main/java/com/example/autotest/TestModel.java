package com.example.autotest;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class TestModel {
    private String question;
    private int answerIndex;
    private int image;
    private ArrayList<String> answers;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public TestModel(String question, int answerIndex, ArrayList<String> answers, int image) {
        this.question = question;
        this.answerIndex = answerIndex;
        this.answers = answers;
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }



    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "question='" + question + '\'' +
                ", answerIndex=" + answerIndex +
                ", image=" + image +
                ", answers=" + Arrays.toString(answers.toArray()) +
                '}';
    }
}

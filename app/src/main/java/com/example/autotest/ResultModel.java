package com.example.autotest;

public class ResultModel {
    int image;
    String question;
    String answer;
    int isTrue;

    public ResultModel(int image, String question, String answer, int isTrue) {
        this.image = image;
        this.question = question;
        this.answer = answer;
        this.isTrue = isTrue;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(int isTrue) {
        this.isTrue = isTrue;
    }
}

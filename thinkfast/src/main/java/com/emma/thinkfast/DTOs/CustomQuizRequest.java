package com.emma.thinkfast.DTOs;

public class CustomQuizRequest implements RequestDTO {

    private String[] categories;
    private int quizLength;

    public String[] getCategories() {
        return this.categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public int getQuizLength() {
        return this.quizLength;
    }

    public void setQuizLength(int quizLength) {
        this.quizLength = quizLength;
    }
}

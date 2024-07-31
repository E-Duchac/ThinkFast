package com.emma.thinkfast.models;

public class RequestDTO {
    
    /* Temporary structure built to parse out request bodies for 
    * the composeCustomQuiz() endpoint in QuestionController.
    * Likely to become more generic, in order to meet the needs
    * of parsing different types of request bodies as needed.
    */

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

package com.emma.thinkfast.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.emma.thinkfast.enums.Category;

public class Question {
    @Id
    private String _id;
    private String questionText;
    private List<String> answerText;
    private Category category;
    private String quizId;

    public Question() {
        super();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswerText() {
        return answerText;
    }

    public void setAnswerText(List<String> answerText) {
        this.answerText = answerText;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((questionText == null) ? 0 : questionText.hashCode());
        result = prime * result + ((answerText == null) ? 0 : answerText.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((quizId == null) ? 0 : quizId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (_id == null) {
            if (other._id != null)
                return false;
        } else if (!_id.equals(other._id))
            return false;
        if (questionText == null) {
            if (other.questionText != null)
                return false;
        } else if (!questionText.equals(other.questionText))
            return false;
        if (answerText == null) {
            if (other.answerText != null)
                return false;
        } else if (!answerText.equals(other.answerText))
            return false;
        if (category != other.category)
            return false;
        if (quizId == null) {
            if (other.quizId != null)
                return false;
        } else if (!quizId.equals(other.quizId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Question [_id=" + _id + ", questionText=" + questionText + ", answerText=" + answerText + ", category="
                + category + ", quizId=" + quizId + "]";
    }
}

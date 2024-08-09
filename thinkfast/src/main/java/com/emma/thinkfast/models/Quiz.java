package com.emma.thinkfast.models;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Quiz {
    @Id
    private String _id;
    private String description;
    private List<String> questionIds;

    public Quiz() {
        super();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((questionIds == null) ? 0 : questionIds.hashCode());
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
        Quiz other = (Quiz) obj;
        if (_id == null) {
            if (other._id != null)
                return false;
        } else if (!_id.equals(other._id))
            return false;
        if (description != other.description)
            return false;
        if (questionIds == null) {
            if (other.questionIds != null)
                return false;
        } else if (!questionIds.equals(other.questionIds))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[id=" + _id + ", description=" + description + ", questionIds=" + questionIds + "]";
    }
}

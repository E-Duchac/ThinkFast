package com.emma.thinkfast.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.emma.thinkfast.enums.Category;

public class User {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Category> faveCategories;

    private String encPw;
    private String pwSalt;

    public User() {
        super();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Category> getFaveCategories() {
        return faveCategories;
    }

    public void setFaveCategories(List<Category> faveCategories) {
        this.faveCategories = faveCategories;
    }

    public String getEncPw() {
        return encPw;
    }

    public void setEncPw(String encPw) {
        this.encPw = encPw;
    }

    public String getPwSalt() {
        return pwSalt;
    }

    public void setPwSalt(String pwSalt) {
        this.pwSalt = pwSalt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((faveCategories == null) ? 0 : faveCategories.hashCode());
        result = prime * result + ((encPw == null) ? 0 : encPw.hashCode());
        result = prime * result + ((pwSalt == null) ? 0 : pwSalt.hashCode());
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
        User other = (User) obj;
        if (_id == null) {
            if (other._id != null)
                return false;
        } else if (!_id.equals(other._id))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (faveCategories == null) {
            if (other.faveCategories != null)
                return false;
        } else if (!faveCategories.equals(other.faveCategories))
            return false;
        if (encPw == null) {
            if (other.encPw != null)
                return false;
        } else if (!encPw.equals(other.encPw))
            return false;
        if (pwSalt == null) {
            if (other.pwSalt != null)
                return false;
        } else if (!pwSalt.equals(other.pwSalt))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", faveCategories=" + faveCategories + ", encPw=" + encPw + ", pwSalt=" + pwSalt + "]";
    }

    /* 
    Need to record a password, salt, etc...may need to build
    a microservice structure specifically for security.
    */

    
}

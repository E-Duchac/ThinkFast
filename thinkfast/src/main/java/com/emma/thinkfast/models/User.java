package com.emma.thinkfast.models;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emma.thinkfast.enums.Category;
import com.emma.thinkfast.enums.Role;

public class User implements UserDetails {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private String encPassword;
    private String pwSalt;
    private String username;
    private Role role;
    private List<GrantedAuthority> authorities;

    private List<Category> faveCategories;

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

    public String getPwSalt() {
        return pwSalt;
    }

    public void setPwSalt(String pwSalt) {
        this.pwSalt = pwSalt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        result = prime * result + ((encPassword == null) ? 0 : encPassword.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((pwSalt == null) ? 0 : pwSalt.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
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
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (encPassword == null) {
            if (other.encPassword != null)
                return false;
        } else if (!encPassword.equals(other.encPassword))
            return false;
        if (pwSalt == null) {
            if (other.pwSalt != null)
                return false;
        } else if (!pwSalt.equals(other.pwSalt))
            return false;
        if (role == null) {
            if (other.role == null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (authorities == null) {
            if (other.authorities == null)
                return false;
        } else if (!authorities.equals(other.authorities))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", faveCategories=" + faveCategories + ", encPassword=" + encPassword + ", pwSalt=" + pwSalt + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return encPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
}

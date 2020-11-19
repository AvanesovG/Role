package com.example.roles.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class User {

    @Id
    String login;

    String name;
    String password;

    public User() {
    }

    public User(String login, String name, String password, Set<UserRole> userRoleSet) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.userRoleSet = userRoleSet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    Set<UserRole> userRoleSet;
}

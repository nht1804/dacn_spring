package com.nttu.dacnsv.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private String userName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private Role role;
    private UsersDetail information;

    public User(String userName, String password, Role role, UsersDetail information) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.information = information;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UsersDetail getInformation() {
        return information;
    }

    public void setInformation(UsersDetail information) {
        this.information = information;
    }
}

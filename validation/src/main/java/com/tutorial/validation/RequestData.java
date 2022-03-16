package com.tutorial.validation;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestData {

    @NotNull(message = "username should not be null")
    @Size(min = 5 , max = 20, message = "Username should be around 5 to 20 charecters")
    private String username;
    @NotNull(message = "password should not be null")
    @Length(min=8 , max = 12 , message = "password should be around 8 to 12 charecters")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

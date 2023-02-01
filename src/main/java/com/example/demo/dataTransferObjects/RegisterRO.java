package com.example.demo.dataTransferObjects;

import java.time.LocalDate;

public class RegisterRO {

    private String email;

    private String nickName;

    private String bio;

    private String password;

    private LocalDate dob;

    public RegisterRO(String email, String nickName, String bio, String password, LocalDate dob) {
        this.email = email;
        this.nickName = nickName;
        this.bio = bio;
        this.password = password;
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}

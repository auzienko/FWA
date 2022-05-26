package edu.school21.cinema.models;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class User extends Entity{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
    private Long avatarId;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public void toSessionAttributes(HttpSession httpSession){
        httpSession.setAttribute("id", getId());
        httpSession.setAttribute("email", getEmail());
        httpSession.setAttribute("firstName", getFirstName());
        httpSession.setAttribute("lastName", getLastName());
        httpSession.setAttribute("phoneNumber", getPhoneNumber());
        httpSession.setAttribute("avatarId", getAvatarId());
    }
}

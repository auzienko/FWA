package edu.school21.cinema.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserAuthHistory extends Entity{
    private LocalDateTime dateTime;
    private String ip;

    private Long userId;

    public UserAuthHistory() {
    }

    public UserAuthHistory(LocalDateTime dateTime, String ip, Long userId) {
        this.dateTime = dateTime;
        this.ip = ip;
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String toDateTimeString(String pattern){
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}

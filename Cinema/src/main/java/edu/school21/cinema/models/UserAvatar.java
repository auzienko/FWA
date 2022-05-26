package edu.school21.cinema.models;

public class UserAvatar extends Entity {
    private String fileName;
    private Long size;
    private String mime;
    private Long userId;

    public UserAvatar() {
    }

    public UserAvatar(String fileName, Long size, String mime, Long userId) {
        this.fileName = fileName;
        this.size = size;
        this.mime = mime;
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String sizeToString() {
        if (size < 1024) return size + "B";
        else if (size / 1024 < 1024) return String.format("%.2fKB", size / 1024.);
        return String.format("%.2fMB", size / 1024. / 1024.);
    }
}

package com.example.sns_project.postpack;

public class Post {

    String title;
    Integer image;
    String subtitle;
    String date;

    public Post(String title, Integer image, String subtitle, String date) {
        this.title = title;
        this.image = image;
        this.subtitle = subtitle;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

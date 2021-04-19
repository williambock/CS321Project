package com.example.project;

import java.util.Date;

public class Post {
    private String post, userID;
    Date date;
    public Post(){

    }

    public Post(String post, String userID){
        this.post = post;
        this.userID = userID;
        date = new Date();
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setPost(String post) {
        this.post = post;
    }

    public String getUserID() {
        return userID;
    }

    public String getPost() {
        return post;
    }

    public Date getDate() {return date;}
}

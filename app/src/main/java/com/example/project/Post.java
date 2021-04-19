package com.example.project;

public class Post {
    private String post, userID;
    public Post(){

    }

    public Post(String post, String userID){
        this.post = post;
        this.userID = userID;
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
}

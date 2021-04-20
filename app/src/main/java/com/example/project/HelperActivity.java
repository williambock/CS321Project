package com.example.project;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import io.grpc.LoadBalancer;

public class HelperActivity{
    private static HelperActivity instance;

    public Post[] helperPosts;
    public boolean startUp = true;
    private HelperActivity(){};

    public static synchronized HelperActivity getInstance(){
        if(instance==null){
            instance=new HelperActivity();
        }
        return instance;
    }
}

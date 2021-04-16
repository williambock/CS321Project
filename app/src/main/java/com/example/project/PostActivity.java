package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    private Button UpdatePostButton;  //Button to Submit a Post
    private Button BackToMainButton;  //Button to send user back to main
    private EditText NewPostText;     //Spot to enter text
    private String NewPostTextString; //String value for the text put in NewPostText
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser currentFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        UpdatePostButton = (Button) findViewById(R.id.UpdatePostButton);
        BackToMainButton = (Button) findViewById(R.id.BackToMainButton);
        NewPostText = (EditText) findViewById(R.id.NewPostText);

        UpdatePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidatePostInfo();
            }
        });

        BackToMainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SendUserToMainActivity();
            }
        });
    }
    /*
     * Gets the message that is to be posted
     */
    private void ValidatePostInfo() {
        NewPostTextString = NewPostText.getText().toString(); //Get the text from the input and convert to String
        if (NewPostTextString.length() == 0) {
            //Tell the user the post is empty
            //Not yet implemented
        }
        else {
            StorePostToStorage();
            SendUserToMainActivity();
        }

    }
    /*
     * Returns the user to the main page
     */
    private void SendUserToMainActivity() {
        Intent MainActivityIntent = new Intent(PostActivity.this, MainActivity.class);
        startActivity(MainActivityIntent);
    }
    /*
     * Is intended to store the message to a firebase collection "messages"
     */
    private void StorePostToStorage() {
         currentFirebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String post = NewPostTextString;
        String userID = currentFirebaseUser.getUid();
        Post message = new Post(post, userID);
        db.collection("messages").add(message);
    }
}
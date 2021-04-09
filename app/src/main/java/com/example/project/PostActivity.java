package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PostActivity extends AppCompatActivity {

    private Button postButton;
    private EditText postText;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference userReference, postReference;

    private String user_id, date, time, post_id, download, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();

        userReference = FirebaseDatabase.getInstance().getReference().child("Users");
        postReference = FirebaseDatabase.getInstance().getReference().child("Posts");
        postText = (EditText) findViewById(R.id.posted_text);
        postButton = (Button) findViewById(R.id.post_button);

        postButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validatePost();
            }
        });
    }


    private void validatePost(){
        description = postText.getText().toString();
        //saveToDatabase();
    }

    /*private void saveToDatabase(){
        userReference.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String username = snapshot.child("fullname").getValue().toString();

                    HashMap postHashMap = new HashMap();
                        postHashMap.put("uid", user_id);
                        postHashMap.put("date", date);
                        postHashMap.put("time", time);
                        postHashMap.put("description", description);
                        postHashMap.put("postimage", download);
                        postHashMap.put("fullname", username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}
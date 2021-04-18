package com.example.project;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {

        private EditText email, password;
        private Button register;

        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register_user);

            //Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
            //startActivity(intent);

            mAuth = FirebaseAuth.getInstance();
            email=findViewById(R.id.Email);
            password=findViewById(R.id.Password);
            register =findViewById(R.id.Register);

            register.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    registerUser();
                }
            });
        }

        private void registerUser() {
            String userEmail, userPassword;
            userEmail = email.getText().toString();
            userPassword = password.getText().toString();
            //Toast.makeText(getApplicationContext(), userEmail, Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), userPassword, Toast.LENGTH_LONG).show();
            if (TextUtils.isEmpty(userEmail)) {
                Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(userPassword)) {
                Toast.makeText(getApplicationContext(), "Please enter Password...", Toast.LENGTH_LONG).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(RegisterUser.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterUser.this, RegisterUser.class);
                                startActivity(intent);
                            }
                        }
                    });
        }
    }
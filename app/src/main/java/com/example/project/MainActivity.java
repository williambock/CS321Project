package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.ui.main.SectionsPagerAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private Button settingsBtn;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference messagesRef = db.collection("messages");
    private Post[] allPosts;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPost();

        setContentView(R.layout.activity_main);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), allPosts);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        System.out.println("ATTEMPTING TO LOAD POSTS &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPostActivity();
            }
        });

        settingsBtn = findViewById(R.id.idBtnSettings);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });

    }

    private void loadPost() {
        System.out.println("ATTEMPTING MESSAGES REF GET &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        messagesRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        System.out.println("STARTING POST READ &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                        allPosts = new Post[queryDocumentSnapshots.size()];
                        System.out.println("Created new post array of size "+queryDocumentSnapshots.size()+" &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                        int counter = 0;

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Post post = documentSnapshot.toObject(Post.class);
                            allPosts[counter] = post;
                            String message = allPosts[counter].getPost();
                            String userID = allPosts[counter].getUserID();
//                            String date = post.getDate().toString();
                            System.out.println(message + " " + userID + "\n");
                            counter++;
                        }
                        //sectionsPagerAdapter.updatePostList(allPosts);
                        HelperActivity helper = HelperActivity.getInstance();
                        helper.helperPosts = allPosts;
                        System.out.println("POSTS HAVE LOADED, POSTS HAVE UPDATED &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    }
                });
        /*messagesRef.orderBy("date", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        System.out.println("STARTING POST READ &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            Post post = documentSnapshot.toObject(Post.class);
                            String message = post.getPost();
                            String userID = post.getUserID();
                            System.out.println(message + "\n" + userID + "\n");
                        }

                        System.out.println("POSTS HAVE LOADED &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    }
                });*/
    }

    private void newPostActivity() {
        Intent newPostIntent = new Intent(MainActivity.this, PostActivity.class);
        startActivity(newPostIntent);
    }

}
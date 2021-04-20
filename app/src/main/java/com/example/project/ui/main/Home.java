package com.example.project.ui.main;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.Post;
import com.example.project.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static Post[] postList;

    // TODO: Rename and change types of parameters
    public View view;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference messagesRef = db.collection("messages");

    private NavigationView navView;
    private DrawerLayout drawLayout;
    private RecyclerView posts;

    //private Post[] postList;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(Post[] postsList) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        postList = postsList;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ONCREATE $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        System.out.println("ONCREATEVIEW ########################################################################");

        view = inflater.inflate(R.layout.fragment_home, container, false);
        navView = (NavigationView) view.findViewById(R.id.navig_view);
        drawLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        posts = (RecyclerView) view.findViewById(R.id.all_users_post_list);
        posts.setHasFixedSize(true);

        return view;
    }

    public void onViewCreated (View view, Bundle savedInstanceState){
        System.out.println("ONVIEWCREATED IS RUNNING ^^^^^^^^^^^^^^^^");
        LinearLayoutManager linearManager = new LinearLayoutManager(getContext());
        linearManager.setReverseLayout(true);
        linearManager.setStackFromEnd(true);
        posts.setLayoutManager(linearManager);
        posts.setLayoutManager(linearManager);


        if(postList != null) {
            RecyclerAdapter adapter = new RecyclerAdapter(getContext(), postList);
            posts.setAdapter(adapter);
        }
        else {
            System.out.println("postList is null, skipping");
        }
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), postList);
        posts.setAdapter(adapter);


    }
}
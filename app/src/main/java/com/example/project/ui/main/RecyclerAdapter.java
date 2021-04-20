package com.example.project.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.HelperActivity;
import com.example.project.Post;
import com.example.project.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public Post[] postList;
    Context context;

    public RecyclerAdapter(Context ctx, Post[] posts) {

        if(posts == null){

            HelperActivity helper = HelperActivity.getInstance();
            postList = helper.helperPosts;
            if(postList == null){

                postList = new Post[10];
                postList[1] =  new Post("This is a test post", "1");
                postList[2] =  new Post("This is a test post", "2");
                postList[3] =  new Post("This is a test post", "3");
                postList[4] =  new Post("This is a test post", "4");
                postList[5] =  new Post("This is a test post", "5");
                postList[6] =  new Post("This is a test post", "6");
                postList[7] =  new Post("This is a test post", "7");
                postList[8] =  new Post("This is a test post", "8");
                postList[9] =  new Post("This is a test post", "9");
                postList[0] =  new Post("This is a test post", "0");
            }
        }
        else {
            postList = posts;
        }

        context = ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, postText, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postText = itemView.findViewById(R.id.post_text);
            date = itemView.findViewById(R.id.current_date);
            id = itemView.findViewById(R.id.poster_id);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        //holder.date.setText(postList[position].getDate().toString());
        holder.postText.setText(postList[position].getPost());
        holder.id.setText(postList[position].getUserID());
    }

    @Override
    public int getItemCount() {
        return postList.length;
    }
}

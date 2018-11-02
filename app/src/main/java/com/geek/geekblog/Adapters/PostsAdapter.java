package com.geek.geekblog.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.geek.geekblog.Models.Post;
import com.geek.geekblog.R;
import com.geek.geekblog.ViewHolders.PostsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsViewHolder> {
    private Context context;
    private ArrayList<Post> posts;

    public PostsAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item,parent,false);
        PostsViewHolder holder = new PostsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.tv_user_name.setText(post.user_name);
        holder.tv_time.setText(post.timestamp);
        holder.tv_post_content.setText(post.content);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}

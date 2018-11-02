package com.geek.geekblog.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.geek.geekblog.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tv_user_name)
    public TextView tv_user_name;
    @BindView(R.id.tv_time)
    public TextView tv_time;
    @BindView(R.id.tv_post_content)
    public TextView tv_post_content;
    public PostsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

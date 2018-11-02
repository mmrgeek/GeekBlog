package com.geek.geekblog.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.geek.geekblog.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatsViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tv_room_name)
    public TextView tv_room_name;
    @BindView(R.id.tv_room_desc)
    public TextView tv_room_desc;
    public ChatsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}

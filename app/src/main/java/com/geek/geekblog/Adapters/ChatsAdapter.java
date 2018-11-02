package com.geek.geekblog.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geek.geekblog.ChatActivity;
import com.geek.geekblog.Models.ChatRoom;
import com.geek.geekblog.R;
import com.geek.geekblog.ViewHolders.ChatsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsViewHolder>{
    private Context context;
    private ArrayList<ChatRoom> chatRooms;

    public ChatsAdapter(Context context, ArrayList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
        this.context = context;
    }

    @NonNull
    @Override

    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item,parent,false);
        ChatsViewHolder holder = new ChatsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position) {
        final ChatRoom room = chatRooms.get(position);
        holder.tv_room_name.setText(room.room_name);
        holder.tv_room_desc.setText(room.room_desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ChatActivity.class);
                i.putExtra("room_id",room.id);
                i.putExtra("room_name",room.room_name);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatRooms.size();
    }
}

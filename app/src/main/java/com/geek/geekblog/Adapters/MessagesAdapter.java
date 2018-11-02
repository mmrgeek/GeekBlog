package com.geek.geekblog.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.geek.geekblog.Models.Message;
import com.geek.geekblog.Models.MessageType;
import com.geek.geekblog.R;
import com.geek.geekblog.Utils.SessionManager;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Message> messages;
    private Context context;

    public MessagesAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case MessageType.SENT_TEXT:
                return new SentTextViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.sent_message_layout,parent,false));
            case MessageType.SENT_IMAGE:
                return new SentImageViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.sent_image_layout,parent,false));
            case MessageType.RECEIVED_TEXT:
                return new ReceivedTextViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.received_message_layout,parent,false));
            case MessageType.RECEIVED_IMAGE:
                return new ReceivedImageViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.received_image_layout,parent,false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        Message message = messages.get(position);
        switch (type){
            case MessageType.SENT_TEXT:
                SentTextViewHolder mholder = (SentTextViewHolder) holder;
                mholder.tv_time.setText(message.timestamp);
                mholder.tv_message.setText(message.content);
                break;
            case MessageType.RECEIVED_TEXT:
                ReceivedTextViewHolder rholder = (ReceivedTextViewHolder) holder;
                rholder.tv_time.setText(message.timestamp);
                rholder.tv_message.setText(message.content);
                rholder.tv_username.setText(message.user_name);
                break;
            case MessageType.SENT_IMAGE:
                SentImageViewHolder sholder = (SentImageViewHolder) holder;
                sholder.tv_time.setText(message.timestamp);
                Picasso.get().load(message.content).into(sholder.iv_image);
                break;
            case MessageType.RECEIVED_IMAGE:
                ReceivedImageViewHolder riholder = (ReceivedImageViewHolder) holder;
                riholder.tv_time.setText(message.timestamp);
                riholder.tv_username.setText(message.user_name);
                Picasso.get().load(message.content).into(riholder.iv_image);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        switch (message.type){
            case 1:
                if (message.id == SessionManager.getUserId(context))
                    return MessageType.SENT_TEXT;
                else
                    return MessageType.RECEIVED_TEXT;
            case 2:
                if (message.id == SessionManager.getUserId(context))
                    return MessageType.SENT_IMAGE;
                else
                    return MessageType.RECEIVED_IMAGE;
            default:
                return super.getItemViewType(position);
        }
    }

    class SentMessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tv_time;

        public SentMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class SentTextViewHolder extends SentMessageViewHolder{
        @BindView(R.id.tv_message)
        TextView tv_message;
        public SentTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class SentImageViewHolder extends SentMessageViewHolder{
        @BindView(R.id.iv_image)
        ImageView iv_image;
        public SentImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_username)
        TextView tv_username;
        @BindView(R.id.tv_time)
        TextView tv_time;

        public ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ReceivedTextViewHolder extends ReceivedMessageViewHolder{
        @BindView(R.id.tv_message)
        TextView tv_message;
        public ReceivedTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ReceivedImageViewHolder extends ReceivedMessageViewHolder{
        @BindView(R.id.iv_image)
        ImageView iv_image;
        public ReceivedImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

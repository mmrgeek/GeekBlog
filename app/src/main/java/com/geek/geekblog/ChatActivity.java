package com.geek.geekblog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.geek.geekblog.Adapters.MessagesAdapter;
import com.geek.geekblog.Models.ChatRoom;
import com.geek.geekblog.Models.MainResponse;
import com.geek.geekblog.Models.Message;
import com.geek.geekblog.Utils.SessionManager;
import com.geek.geekblog.WebService.Server;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.rv_chat)
    RecyclerView rv_chat;

    @BindView(R.id.et_message)
    EditText et_message;

    @BindView(R.id.tv_chat_room_name)
    TextView tv_roomname;

    private int room_id;
    private LinearLayoutManager manager;
    private int user_id;
    private ArrayList<Message> messages = new ArrayList<>();
    private MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        tv_roomname.setText(getIntent().getExtras().getString("room_name"));
        room_id = getIntent().getExtras().getInt("room_id");
        user_id = SessionManager.getUserId(this);
        manager = new LinearLayoutManager(this);
        rv_chat.setLayoutManager(manager);
        Message message = new Message();
        message.id = 1;
        message.timestamp = "2:00";
        message.user_id = 20;
        message.user_name = "Ahmed";
        message.content = "hi guys ....";
        message.type = 1;
        message.room_id = room_id;
        messages.add(message);
        adapter = new MessagesAdapter(messages,this);
        rv_chat.setAdapter(adapter);
    }



    @OnClick(R.id.b_send)
    public void onClick(View view){
        Message message = new Message();

        if (!et_message.getText().toString().trim().isEmpty()){
            message.type = 1;
            message.content = et_message.getText().toString();
            message.room_id = room_id;
            message.user_id = user_id;
            message.user_name = SessionManager.getUserName(this);
            message.id = 0;
            message.timestamp = null;
            messages.add(message);

            adapter.notifyItemInserted(messages.size()-1);
            rv_chat.scrollToPosition(messages.size()-1);
            et_message.setText("");
        }
    }
}

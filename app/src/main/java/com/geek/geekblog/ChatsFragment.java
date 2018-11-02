package com.geek.geekblog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.geek.geekblog.Adapters.ChatsAdapter;
import com.geek.geekblog.Models.ChatRoom;
import com.geek.geekblog.WebService.Server;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatsFragment extends Fragment {
    @BindView(R.id.rv_chats)
    RecyclerView rv_chats;
    @BindView(R.id.loading_bar_chats)
    ProgressBar loading_bar;
    private LinearLayoutManager layoutManager;
    private ChatsAdapter adapter;
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_chats, container, false);
        ButterKnife.bind(this,v);
        layoutManager = new LinearLayoutManager(v.getContext());
        rv_chats.setLayoutManager(layoutManager);
        loading_bar.setVisibility(View.VISIBLE);



        Server.getInstance().getApi().getAllChats().enqueue(new Callback<ArrayList<ChatRoom>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ChatRoom>> call, @NonNull Response<ArrayList<ChatRoom>> response) {
                if(response.body()!=null) {
                    adapter = new ChatsAdapter(v.getContext(), response.body());
                    rv_chats.setAdapter(adapter);
                    loading_bar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ChatRoom>> call, @NonNull Throwable t) {
                Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                loading_bar.setVisibility(View.GONE);
            }
        });
        return v;
    }
}

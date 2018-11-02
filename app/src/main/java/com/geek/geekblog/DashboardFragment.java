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

import com.geek.geekblog.Adapters.PostsAdapter;
import com.geek.geekblog.Models.Post;
import com.geek.geekblog.WebService.Server;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    @BindView(R.id.rv_posts)
    RecyclerView rv_posts;
    @BindView(R.id.loading_bar)
    ProgressBar loading_bar;
    private LinearLayoutManager layoutManager;
    private PostsAdapter adapter;
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this,v);
        layoutManager = new LinearLayoutManager(this.getContext());
        rv_posts.setLayoutManager(layoutManager);
        loading_bar.setVisibility(View.VISIBLE);
        Server.getInstance().getApi().getAllPosts().enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Post>> call, @NonNull Response<ArrayList<Post>> response) {
                if(response.body()!= null) {
                    adapter = new PostsAdapter(v.getContext(), response.body());
                    rv_posts.setAdapter(adapter);
                    loading_bar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(v.getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Post>> call, @NonNull Throwable t) {
                Toast.makeText(v.getContext(), call.toString(), Toast.LENGTH_SHORT).show();
                loading_bar.setVisibility(View.GONE);
            }
        });
        return v;
    }
}

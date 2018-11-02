package com.geek.geekblog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geek.geekblog.Models.MainResponse;
import com.geek.geekblog.Models.Post;
import com.geek.geekblog.Utils.SessionManager;
import com.geek.geekblog.WebService.API;
import com.geek.geekblog.WebService.Server;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {
    @BindView(R.id.et_post)
    EditText et_post;
    @BindView(R.id.b_submit)
    Button b_submit;
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this,v);
        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_post.getText().toString().trim().isEmpty()){
                    Post post = new Post();
                    post.content = et_post.getText().toString();
                    post.content_type = 1;
                    post.user_id = SessionManager.getUserId(v.getContext());
                    post.user_name = SessionManager.getUserName(v.getContext());
                    Server.getInstance().getApi().AddPost(post).enqueue(new Callback<MainResponse>() {
                        @Override
                        public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                            et_post.setText("");
                            Toast.makeText(PostFragment.this.v.getContext(), response.body().message, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<MainResponse> call, Throwable t) {
                            Toast.makeText(PostFragment.this.v.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return v;
    }
}

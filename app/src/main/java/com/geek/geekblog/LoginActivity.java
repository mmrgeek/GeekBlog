package com.geek.geekblog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.geek.geekblog.Models.LoginResponse;
import com.geek.geekblog.Models.User;
import com.geek.geekblog.Utils.SessionManager;
import com.geek.geekblog.WebService.Server;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("logging in....");
        progressDialog.setCancelable(false);
        if (SessionManager.isLoggedIn(this)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
    }
    @OnClick(R.id.tv_register)
    public void tv_register(){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
    @OnClick(R.id.b_login)
    public void b_login(){
        if (et_email.getText().toString().trim().isEmpty())
            et_email.setError("email is missing");
        else if (et_password.getText().toString().trim().isEmpty())
            et_password.setError("password is missing");
        else {
            progressDialog.show();
            User user = new User();
            user.email = et_email.getText().toString();
            user.password = et_password.getText().toString();
            //user.user_name = "";
            Server.getInstance().getApi().LoginUser(user).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    et_email.setText("");
                    et_password.setText("");
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    if(response.body().status == 1){
                        SessionManager.LoginUser(LoginActivity.this,response.body().user);
                        Toast.makeText(LoginActivity.this, SessionManager.getUserName(LoginActivity.this)
                                , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        LoginActivity.this.finish();
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

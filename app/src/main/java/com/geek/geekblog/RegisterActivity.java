package com.geek.geekblog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.geek.geekblog.Models.MainResponse;
import com.geek.geekblog.Models.User;
import com.geek.geekblog.WebService.Server;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.et_username_reg)
    EditText et_username;
    @BindView(R.id.et_email_reg)
    EditText et_email;
    @BindView(R.id.et_password_reg)
    EditText et_password;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Register user....");
        progressBar.setCancelable(false);
    }
    @OnClick(R.id.b_register)
    public void b_register(){
        if (et_email.getText().toString().trim().isEmpty())
            et_email.setError("email is missing");
        else if(et_username.getText().toString().trim().isEmpty())
            et_username.setError("username is missing");
        else if(et_password.getText().toString().trim().isEmpty())
            et_password.setError("password is missing");
        else{
            User user = new User();
            user.username = et_username.getText().toString();
            user.email = et_email.getText().toString();
            user.password = et_password.getText().toString();
            progressBar.show();
            Server.getInstance().getApi().RegisterUser(user).enqueue(new Callback<MainResponse>() {
                @Override
                public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                    et_email.setText("");
                    et_username.setText("");
                    et_password.setText("");
                    Toast.makeText(RegisterActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    if (response.body().status == 1) {
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        RegisterActivity.this.finish();
                    }
                    progressBar.dismiss();
                }

                @Override
                public void onFailure(Call<MainResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

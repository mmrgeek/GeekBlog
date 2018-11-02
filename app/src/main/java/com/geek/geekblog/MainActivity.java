package com.geek.geekblog;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.geek.geekblog.Utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.nav_bar)
    BottomNavigationView nav_bar;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.FAB_post)
    FloatingActionButton FAB_post;
    private static boolean post_is_loaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LoadFragment(getSupportFragmentManager().beginTransaction(),new DashboardFragment());
        tv_name.setText(SessionManager.getUserName(this));
        nav_bar.setSelectedItemId(R.id.dashboard);
        nav_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        LoadFragment(getSupportFragmentManager().beginTransaction(),new DashboardFragment());
                        return true;
                    case R.id.chats:
                        LoadFragment(getSupportFragmentManager().beginTransaction(),new ChatsFragment());
                        return true;
                    case R.id.settings:
                        LoadFragment(getSupportFragmentManager().beginTransaction(),new SettingsFragment());
                        return true;
                    default:
                        return false;
                }
            }
        });
        FAB_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadFragment(getSupportFragmentManager().beginTransaction(),new PostFragment());
                post_is_loaded = true;
            }
        });
    }
    public void LoadFragment(FragmentTransaction fragmentTransaction, Fragment fragment){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Replacable,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (post_is_loaded)
            LoadFragment(getSupportFragmentManager().beginTransaction(), new DashboardFragment());

        else
            super.onBackPressed();
    }
}

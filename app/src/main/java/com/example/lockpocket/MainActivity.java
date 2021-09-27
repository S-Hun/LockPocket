package com.example.lockpocket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.example.lockpocket.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private FragmentTransaction transaction;

    private DrawerLayout drawerLayout;
    private Context context;
    private Toolbar toolbar;

    private View drawerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.task_frame, homeFragment).commitAllowingStateLoss();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        // ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        // actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        // actionBar.setHomeAsUpIndicator(R.drawable.button_back); //뒤로가기 버튼 이미지 지정

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        drawerButton = findViewById(R.id.drawer_btn);
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

//    /*
//    hardkgosai by stackoverflow(2021-06-27)
//    https://stackoverflow.com/questions/21724420/how-to-hide-navigation-bar-permanently-in-android-activity
//     */
//    public void hideSystemUI(Window window) { //pass getWindow();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//
//            window.getInsetsController().hide(WindowInsets.Type.systemBars());
//
//        } else {
//
//            View decorView = window.getDecorView();
//
//            int uiVisibility = decorView.getSystemUiVisibility();
//
//            uiVisibility |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
//            uiVisibility |= View.SYSTEM_UI_FLAG_FULLSCREEN;
//            uiVisibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                uiVisibility |= View.SYSTEM_UI_FLAG_IMMERSIVE;
//                uiVisibility |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            }
//
//            decorView.setSystemUiVisibility(uiVisibility);
//        }
//    }
}
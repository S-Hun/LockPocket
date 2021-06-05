package com.example.lockpocket;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.lockpocket.account.LoginActivity;
import com.example.lockpocket.account.PreferenceManager;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    public static  Context context_main;
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context_main = this;
        ImageButton MainToMenuButton = (ImageButton) findViewById(R.id.MainToMenubtn);
        ImageButton MainToCommunityButton = (ImageButton) findViewById(R.id.ToCommunitybtn);
        ImageButton MainToHomeButton = (ImageButton) findViewById(R.id.ToHomebtn);
        ImageButton MainToEditButton = (ImageButton) findViewById(R.id.ToEditbtn);
        ImageButton DesignToCommunityButton1 = (ImageButton) findViewById(R.id.DesignToCommnitybtn1);
        ImageButton DesignToCommunityButton2 = (ImageButton) findViewById(R.id.DesignToCommunitybtn2);
        Button LoginBtn = (Button) findViewById(R.id.logoutBtn);
        Button QuestionBtn = (Button) findViewById(R.id.QuestionBtn);
        Button HelpBtn = (Button) findViewById(R.id.HelpBtn);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.logoutBtn:
                        PreferenceManager.clear(context_main);
                        Intent ToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                        ToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(ToLogin);
                        finish();
                        break ;
                    case R.id.HelpBtn:
                        Intent DrawerintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
                        startActivity(DrawerintentHelp);
                        break ;
                    case R.id.QuestionBtn:
                        Intent DrawerintentQuestion = new Intent(getApplicationContext(), QuestionActivity.class);
                        startActivity(DrawerintentQuestion);
                        break ;
                }
            }
        };

        LoginBtn.setOnClickListener(onClickListener);
        QuestionBtn.setOnClickListener(onClickListener);
        HelpBtn.setOnClickListener(onClickListener);
        MainToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });
        MainToCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainintentCommunity = new Intent(getApplicationContext(), CommunityActivity.class);
                startActivity(MainintentCommunity);
            }
        });
        MainToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainintentEdit = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainintentEdit);
            }
        });
        MainToEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainintentHelp = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(MainintentHelp);
            }
        });
        DesignToCommunityButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DesignToCommunityButton1 = new Intent(getApplicationContext(), CommunityActivity.class);
                startActivity(DesignToCommunityButton1);
            }
        });
        DesignToCommunityButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DesignToCommunityButton2 = new Intent(getApplicationContext(), CommunityActivity.class);
                startActivity(DesignToCommunityButton2);
            }
        });
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawerView);
        //drawerLayout.setDrawerListener(listener);
    }

}


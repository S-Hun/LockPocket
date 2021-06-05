package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lockpocket.account.LoginActivity;
import com.example.lockpocket.account.PreferenceManager;

public class QuestionActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ImageButton.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.ToCommunitybtn:
                        Intent QuestionintentCommunity= new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(QuestionintentCommunity);
                        break ;
                    case R.id.ToHomebtn:
                        Intent QuestionintentEdit = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(QuestionintentEdit);
                        break ;
                    case R.id.ToEditbtn:
                        Intent QuestionintentHelp = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(QuestionintentHelp);
                        break ;
                }
            }
        } ;
        ImageButton MainToMenuButton = (ImageButton) findViewById(R.id.MainToMenubtn);
        MainToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });
        Button.OnClickListener onClickListener1 = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.logoutBtn:
                        PreferenceManager.clear(mContext);
                        Intent ToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                        ToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(ToLogin);
                        finish();
                        break ;
                    case R.id.HelpBtn:
                        Intent DrawerintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
                        startActivity(DrawerintentHelp);
                        finish();
                        break ;
                    case R.id.QuestionBtn:
                        Intent DrawerintentQuestion = new Intent(getApplicationContext(), QuestionActivity.class);
                        startActivity(DrawerintentQuestion);
                        finish();
                        break ;
                }
            }
        };


        Button LoginBtn = (Button) findViewById(R.id.logoutBtn);
        Button QuestionBtn = (Button) findViewById(R.id.QuestionBtn);
        Button HelpBtn = (Button) findViewById(R.id.HelpBtn);
        ImageButton QuestionToCommunityButton = (ImageButton) findViewById(R.id.ToCommunitybtn);
        ImageButton QuestionToEditButton = (ImageButton) findViewById(R.id.ToHomebtn);
        ImageButton QuestionToHelpButton = (ImageButton) findViewById(R.id.ToEditbtn);
        QuestionToHelpButton.setOnClickListener(onClickListener1);
        QuestionToCommunityButton.setOnClickListener(onClickListener);
        QuestionToEditButton.setOnClickListener(onClickListener);
//        LoginBtn.setOnClickListener(onClickListener1);
//        QuestionBtn.setOnClickListener(onClickListener1);
//        HelpBtn.setOnClickListener(onClickListener1);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawerView);
    }
}


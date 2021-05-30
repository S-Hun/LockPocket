package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class HelpActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button LoginBtn = (Button) findViewById(R.id.loginBtn);
        Button QuestionBtn = (Button) findViewById(R.id.QuestionBtn);
        Button HelpBtn = (Button) findViewById(R.id.HelpBtn);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.loginBtn:
                        Intent DrawerintentLogin = new Intent(getApplicationContext(), UploadActivity.class);
                        startActivity(DrawerintentLogin);
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

        ImageButton.OnClickListener onClickListener1 = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.ToCommunitybtn:
                        Intent HelpintentCommunity= new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(HelpintentCommunity);
                        break ;
                    case R.id.ToHomebtn:
                        Intent HelpintentEdit = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(HelpintentEdit);
                        break ;
                    case R.id.ToEditbtn:
                        Intent HelpintentHelp = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(HelpintentHelp);
                        break ;
                    case R.id.MainToMenubtn:
                        drawerLayout.openDrawer(drawerView);
                        break ;
                }
            }
        } ;
        ImageButton HelpToCommunicationButton = (ImageButton) findViewById(R.id.ToCommunitybtn);
        ImageButton HelpToEditButton = (ImageButton) findViewById(R.id.ToHomebtn);
        ImageButton HelpToHelpButton = (ImageButton) findViewById(R.id.ToEditbtn);
        HelpToCommunicationButton.setOnClickListener(onClickListener1);
        HelpToEditButton.setOnClickListener(onClickListener1);
        HelpToHelpButton.setOnClickListener(onClickListener1);
        ImageButton HelpToMenuButton = (ImageButton) findViewById(R.id.MainToMenubtn);
        HelpToMenuButton.setOnClickListener(onClickListener1);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_help);
        drawerView = (View) findViewById(R.id.drawerView);
    }

}
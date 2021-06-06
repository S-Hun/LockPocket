package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lockpocket.account.LoginActivity;
import com.example.lockpocket.account.PreferenceManager;


public class CommunityActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private View drawerView;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_community);
        Button LoginBtn = (Button) findViewById(R.id.logoutBtn);
        Button QuestionBtn = (Button) findViewById(R.id.QuestionBtn);
        Button HelpBtn = (Button) findViewById(R.id.HelpBtn);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.CommunityToUploadbtn:
                        Intent CommunityintentUpload = new Intent(getApplicationContext(), UploadActivity.class);
                        startActivity(CommunityintentUpload);
                        break ;
                    case R.id.logoutBtn:
                        PreferenceManager.clear(mcontext);
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
        ImageButton.OnClickListener onClickListener2 = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.ToCommunitybtn:
                        Intent CommunityintentCommunity = new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(CommunityintentCommunity);
                        break ;
                    case R.id.ToHomebtn:
                        Intent CommunityintentToEdit = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(CommunityintentToEdit);
                        break ;
                    case R.id.ToEditbtn:
                        Intent CommunityintentHelp = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(CommunityintentHelp);
                        break ;
                    case R.id.MainToMenubtn:
                        drawerLayout.openDrawer(drawerView);
                        break ;
                }
            }
        } ;
        Button CommunityToUploadButton = (Button) findViewById(R.id.CommunityToUploadbtn);
        ImageButton CommunityToMenuButton = (ImageButton) findViewById(R.id.MainToMenubtn);
        ImageButton CommunityToCommunityButton = (ImageButton) findViewById(R.id.ToCommunitybtn);
        ImageButton CommunityToEditButton = (ImageButton) findViewById(R.id.ToHomebtn);
        ImageButton CommunityToHelpButton = (ImageButton) findViewById(R.id.ToEditbtn);
        CommunityToCommunityButton.setOnClickListener(onClickListener2);
        CommunityToEditButton.setOnClickListener(onClickListener2);
        CommunityToHelpButton.setOnClickListener(onClickListener2);
        CommunityToMenuButton.setOnClickListener(onClickListener2);
        CommunityToUploadButton.setOnClickListener(onClickListener);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Commu);
        drawerView = (View) findViewById(R.id.drawerView);
    }
}
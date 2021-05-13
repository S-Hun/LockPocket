package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        Button.OnClickListener onClickListener1 = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.CommunityToUploadbtn:
                        Intent CommunityintentUpload = new Intent(getApplicationContext(), UploadActivity.class);
                        startActivity(CommunityintentUpload);
                        break ;
                }
            }
        } ;
        ImageButton.OnClickListener onClickListener2 = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.CommunityToCommunitybtn:
                        Intent CommunityintentCommunity = new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(CommunityintentCommunity);
                        break ;
                    case R.id.CommunityToEditbtn:
                        Intent CommunityintentToEdit = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(CommunityintentToEdit);
                        break ;
                    case R.id.CommunityToHelpbtn:
                        Intent CommunityintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
                        startActivity(CommunityintentHelp);
                        break ;
                    case R.id.CommunityToMenubtn:
                        Intent CommunityintentMenu = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(CommunityintentMenu);
                        break ;
                }
            }
        } ;
        Button CommunityToUploadButton = (Button) findViewById(R.id.CommunityToUploadbtn);
        ImageButton CommunityToMenuButton = (ImageButton) findViewById(R.id.CommunityToMenubtn);
        ImageButton CommunityToCommunityButton = (ImageButton) findViewById(R.id.CommunityToCommunitybtn);
        ImageButton CommunityToEditButton = (ImageButton) findViewById(R.id.CommunityToEditbtn);
        ImageButton CommunityToHelpButton = (ImageButton) findViewById(R.id.CommunityToHelpbtn);
        CommunityToCommunityButton.setOnClickListener(onClickListener2);
        CommunityToEditButton.setOnClickListener(onClickListener2);
        CommunityToHelpButton.setOnClickListener(onClickListener2);
        CommunityToMenuButton.setOnClickListener(onClickListener2);
        CommunityToUploadButton.setOnClickListener(onClickListener1);

    }
}
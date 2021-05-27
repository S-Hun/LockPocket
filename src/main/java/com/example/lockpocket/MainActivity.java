package com.example.lockpocket;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton mainTomenuButton = (ImageButton) findViewById(R.id.MainToMenubtn);
        ImageButton MainToCommunityButton = (ImageButton) findViewById(R.id.MainToCommunitybtn);
        ImageButton MainToEditButton = (ImageButton) findViewById(R.id.MainToEditbtn);
        ImageButton MainToHelpButton = (ImageButton) findViewById(R.id.MainToHelpbtn);
        ImageButton DesignToCommunityButton1 = (ImageButton) findViewById(R.id.DesignToCommnitybtn1);
        ImageButton DesignToCommunityButton2 = (ImageButton) findViewById(R.id.DesignToCommunitybtn2);
        mainTomenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainintentToMenu = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(MainintentToMenu);
            }
        });
        MainToCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainintentCommunity = new Intent(getApplicationContext(), CommunityActivity.class);
                startActivity(MainintentCommunity);
            }
        });
        MainToEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainintentEdit = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(MainintentEdit);
            }
        });
        MainToHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
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
    }
}


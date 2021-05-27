package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ImageButton.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.HelpToCommunitybtn:
                        Intent HelpintentCommunity= new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(HelpintentCommunity);
                        break ;
                    case R.id.HelpToEditbtn:
                        Intent HelpintentEdit = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(HelpintentEdit);
                        break ;
                    case R.id.HelpToHelpbtn:
                        Intent HelpintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
                        startActivity(HelpintentHelp);
                        break ;
                    case R.id.HelpToMenubtn:
                        Intent HelpintentMenu = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(HelpintentMenu);
                        break ;
                }
            }
        } ;
        ImageButton HelpToCommunicationButton = (ImageButton) findViewById(R.id.HelpToCommunitybtn);
        ImageButton HelpToEditButton = (ImageButton) findViewById(R.id.HelpToEditbtn);
        ImageButton HelpToHelpButton = (ImageButton) findViewById(R.id.HelpToHelpbtn);
        ImageButton HelpToMenuButton = (ImageButton) findViewById(R.id.HelpToMenubtn);
        HelpToCommunicationButton.setOnClickListener(onClickListener);
        HelpToEditButton.setOnClickListener(onClickListener);
        HelpToHelpButton.setOnClickListener(onClickListener);
        HelpToMenuButton.setOnClickListener(onClickListener);
    }

}
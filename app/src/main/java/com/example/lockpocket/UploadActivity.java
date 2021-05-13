package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ImageButton.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.UploadToCommunitybtn:
                        Intent UploadintentCommunity= new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(UploadintentCommunity);
                        break ;
                    case R.id.UploadToEditbtn:
                        Intent UploadintentEdit = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(UploadintentEdit);
                        break ;
                    case R.id.UploadToHelpbtn:
                        Intent UploadintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
                        startActivity(UploadintentHelp);
                        break ;
                    case R.id.UploadToMenubtn:
                        Intent UploadintentMenu = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(UploadintentMenu);
                        break ;
                }
            }
        } ;
        ImageButton UploadToCommunity= (ImageButton) findViewById(R.id.UploadToCommunitybtn);
        ImageButton UploadToEdit = (ImageButton) findViewById(R.id.UploadToEditbtn);
        ImageButton UploadToHelp = (ImageButton) findViewById(R.id.UploadToHelpbtn);
        ImageButton UploadToMenu = (ImageButton) findViewById(R.id.UploadToMenubtn);
        UploadToCommunity.setOnClickListener(onClickListener);
        UploadToEdit.setOnClickListener(onClickListener);
        UploadToHelp.setOnClickListener(onClickListener);
        UploadToMenu.setOnClickListener(onClickListener);
    }

    }


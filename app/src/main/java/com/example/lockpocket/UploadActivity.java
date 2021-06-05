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
                    case R.id.ToCommunitybtn:
                        Intent UploadintentCommunity= new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(UploadintentCommunity);
                        break ;
                    case R.id.ToHomebtn:
                        Intent UploadintentEdit = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(UploadintentEdit);
                        break ;
                    case R.id.ToEditbtn:
                        Intent UploadintentHelp = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(UploadintentHelp);
                        break ;
                    case R.id.MainToMenubtn:

                        break ;
                }
            }
        };
        ImageButton UploadToCommunity= (ImageButton) findViewById(R.id.ToCommunitybtn);
        ImageButton UploadToEdit = (ImageButton) findViewById(R.id.ToHomebtn);
        ImageButton UploadToHelp = (ImageButton) findViewById(R.id.ToEditbtn);
        ImageButton UploadToMenu = (ImageButton) findViewById(R.id.MainToMenubtn);
        UploadToCommunity.setOnClickListener(onClickListener);
        UploadToEdit.setOnClickListener(onClickListener);
        UploadToHelp.setOnClickListener(onClickListener);
        UploadToMenu.setOnClickListener(onClickListener);
    }

    }


package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ImageButton.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.QuestionToCommunitybtn:
                        Intent QuestionintentCommunity= new Intent(getApplicationContext(), CommunityActivity.class);
                        startActivity(QuestionintentCommunity);
                        break ;
                    case R.id.QuestionToEditbtn:
                        Intent QuestionintentEdit = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(QuestionintentEdit);
                        break ;
                    case R.id.QuestionToHelpbtn:
                        Intent QuestionintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
                        startActivity(QuestionintentHelp);
                        break ;
                    case R.id.QuestionToMenubtn:
                        Intent QuestionintentMenu = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(QuestionintentMenu);
                        break ;
                }
            }
        } ;
        ImageButton  QuestionToCommunityButton= (ImageButton) findViewById(R.id.QuestionToCommunitybtn);
        ImageButton QuestionToEditButton = (ImageButton) findViewById(R.id.QuestionToEditbtn);
        ImageButton QuestiongToHelpButton = (ImageButton) findViewById(R.id.QuestionToHelpbtn);
        ImageButton QuestionToMenuButton = (ImageButton) findViewById(R.id.QuestionToMenubtn);
        QuestiongToHelpButton.setOnClickListener(onClickListener);
        QuestionToCommunityButton.setOnClickListener(onClickListener);
        QuestionToEditButton.setOnClickListener(onClickListener);
        QuestionToMenuButton.setOnClickListener(onClickListener);


    }
}


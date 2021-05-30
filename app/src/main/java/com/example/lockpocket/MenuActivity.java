package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity; // 삭제 예
// 삭제 예정
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button MenuToEditButton = (Button) findViewById(R.id.MenuToEditbtn);
        Button MenuToCommunityButton = (Button) findViewById(R.id.MenuToCommunitybtn);
        Button MenuToQuestionButton = (Button) findViewById(R.id.MenuToQuestionbtn);
        Button MenuToHelpButton = (Button) findViewById(R.id.MenuToHelpbtn);
        MenuToEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuintentEdit = new Intent(getApplicationContext(), EditActivityTemp.class);
                startActivity(MenuintentEdit);
            }
        });
        MenuToCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuintentCommunity = new Intent(getApplicationContext(), CommunityActivity.class);
                startActivity(MenuintentCommunity);
            }
        });
        MenuToQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuintentQuestion = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(MenuintentQuestion);
            }
        });
        MenuToHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuintentHelp = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(MenuintentHelp);
            }
        });
    }
}


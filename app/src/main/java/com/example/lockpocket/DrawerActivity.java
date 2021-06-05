package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.lockpocket.account.PreferenceManager;

public class DrawerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.logoutBtn:
                        finishAffinity();
                        System.runFinalization();
                        System.exit(0);
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
        Button LoginBtn = (Button) findViewById(R.id.logoutBtn);
        Button QuestionBtn = (Button) findViewById(R.id.QuestionBtn);
        Button HelpBtn = (Button) findViewById(R.id.HelpBtn);
        LoginBtn.setOnClickListener(onClickListener);
        QuestionBtn.setOnClickListener(onClickListener);
        HelpBtn.setOnClickListener(onClickListener);
    }
}

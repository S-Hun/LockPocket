package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TemplateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        ImageButton homeButton = (ImageButton) findViewById(R.id.home_btn);
        ImageButton templateButton = (ImageButton) findViewById(R.id.template_btn);
        ImageButton communityButton = (ImageButton) findViewById(R.id.community_btn);
        ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.home_btn:
                        HomeButton();
                        break ;
                    case R.id.template_btn:
                        TemplateButton();;
                        break ;
                    case R.id.community_btn:
                        CommunityButton();
                        break ;
                }
            }
        };
        homeButton.setOnClickListener(onClickListener);
        templateButton.setOnClickListener(onClickListener);
        communityButton.setOnClickListener(onClickListener);
    }

    public void HomeButton(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void TemplateButton(){
        Intent intent = new Intent(getApplicationContext(), TemplateActivity.class);
        startActivity(intent);
    }
    public void CommunityButton(){
        Intent intent = new Intent(getApplicationContext(), CommunityActivity.class);
        startActivity(intent);
    }

    public void IntentPreventer(Intent intent){
        intent.setAction(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
    }
}

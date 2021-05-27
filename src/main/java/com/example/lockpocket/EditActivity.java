package com.example.lockpocket;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ImageButton EditToMenuButton = (ImageButton) findViewById(R.id.EditToMenubtn);
        EditToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EditintentMenu = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(EditintentMenu);
            }
        });
    }
}
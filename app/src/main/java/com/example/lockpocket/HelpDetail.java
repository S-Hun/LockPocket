package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HelpDetail extends AppCompatActivity {
    int[] arr = new int[10];
    int pos = 0;
    private FragmentManager fragmentManager;
    private Context context;
    private FragmentTransaction transaction;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        context = this;
        Intent intent = getIntent();
        int helpNum = intent.getIntExtra("num", 0);
        if(helpNum == 1)
        {
            arr[0] = R.drawable.help_main;
            arr[1] = R.drawable.help_edit1;
            arr[2] = R.drawable.help_edit2;
            arr[3] = R.drawable.help_comu;
            arr[4] = R.drawable.help_comu_detail;
            arr[5] = R.drawable.help_template;
        }
        else if(helpNum == 2)
        {
            arr[0] = R.drawable.help_edit1;
            arr[1] = R.drawable.help_edit2;
        }
        else if(helpNum == 3)
        {
            arr[0] = R.drawable.help_upload;
        }
        else if(helpNum == 4)
        {
            arr[0] = R.drawable.help_comu;
            arr[1] = R.drawable.help_comu_detail;
        }
        else if(helpNum == 5)
        {
            arr[0] = R.drawable.help_myprofile;
        }
        FrameLayout layout = findViewById(R.id.help_frame);
        layout.setBackgroundResource(arr[pos]);
        layout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          pos += 1;
                                          if(arr[pos] == 0) finish();
                                          else {
                                              layout.setBackgroundResource(arr[pos]);
                                          }
                                      }
                                  });

    }
}

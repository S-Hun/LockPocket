package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.lockpocket.fragment.HelpDetailFragment;

public class HelpDetail extends AppCompatActivity {
    int[] arr = new int[10];
    private FragmentManager fragmentManager;
    private Context context;
    private FragmentTransaction transaction;
    private HelpDetailFragment helpDetailFragment;
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
            arr[0] = R.drawable.help_comu;
        }
        else if(helpNum == 4)
        {
            arr[0] = R.drawable.help_comu;
            arr[1] = R.drawable.help_comu_detail;
        }
        else if(helpNum == 5)
        {
            arr[0] = R.drawable.help_main;
        }
        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureID", arr);
        helpDetailFragment = new HelpDetailFragment();
        helpDetailFragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.help_frame, helpDetailFragment).commitAllowingStateLoss();

    }
}

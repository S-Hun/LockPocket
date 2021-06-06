  package com.example.lockpocket.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lockpocket.R;

  public class AccountActivity extends AppCompatActivity {
    private TextView tv_id, tv_name;
    private ImageView back;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_account);

        back = findViewById(R.id.Backbtn);
        tv_id = findViewById(R.id.tv_id);
        tv_name = findViewById(R.id.tv_name);
        ImageView.OnClickListener onClickListener = new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        tv_id.setText(PreferenceManager.getString(mContext, "Id"));
        tv_name.setText(PreferenceManager.getString(mContext, "userName"));
        back.setOnClickListener(onClickListener);
    }
}
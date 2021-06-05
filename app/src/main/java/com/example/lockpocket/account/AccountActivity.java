  package com.example.lockpocket.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lockpocket.R;

  public class AccountActivity extends AppCompatActivity {
    private TextView tv_id, tv_name;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_account);

        tv_id = findViewById(R.id.tv_id);
        tv_name = findViewById(R.id.tv_name);

        tv_id.setText(PreferenceManager.getString(mContext, "Id"));
        tv_name.setText(PreferenceManager.getString(mContext, "userName"));
    }
}
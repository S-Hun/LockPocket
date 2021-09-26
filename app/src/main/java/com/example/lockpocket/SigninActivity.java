package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {
    private Context mContext;
    EditText et_email;
    EditText et_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        et_email = findViewById(R.id.emailAddress);
        et_pass = findViewById(R.id.password);
        Button button1 = (Button)findViewById(R.id.login_btn);
        Button button2 = (Button)findViewById(R.id.signin_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {signin();}
        });
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void signin() {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
    private void login() {
//        String userEmail = et_email.getText().toString();
//        String userPass = et_pass.getText().toString();

        // 로그인 성공시
//        if(success){
//          String userEmail = jsonObject.getString() -> 이메일  사용자 정보 가져오기
//          PreferenceManager.setString(mContext, "Email", userEmail); -> 유저 정보 sharedPreference
//          Intent intent = new Intent(SigninActivity.this, MainActivity.class); -> 메인으로 화면 전환
//          startActivity(intent);
//          finish();
//        }
        // 로그인 실패
//        else{
//            return;
//        }
    }
}

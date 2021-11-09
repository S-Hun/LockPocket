package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.lockpocket.request.LoginRequest;
import com.example.lockpocket.utils.AppNetwork;
import com.example.lockpocket.utils.Encryption;
import com.example.lockpocket.utils.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SigninActivity extends AppCompatActivity {
    private Context mContext;
    EditText et_email;
    EditText et_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mContext = getApplicationContext();
        et_email = findViewById(R.id.emailAddress);
        et_pass = findViewById(R.id.password);
        Button button1 = (Button)findViewById(R.id.login_btn);
        Button button2 = (Button)findViewById(R.id.signin_btn);
        View password_toggle = findViewById(R.id.password_toggle);
        password_toggle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean ret = false;
                switch(event.getActionMasked())
                {
                    case MotionEvent.ACTION_DOWN:
                        et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
                        ret = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                        ret = true;
                        break;
                }
                return ret;
            }
        });
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userID = et_email.getText().toString();
                String userPass = et_pass.getText().toString();

                if(userID.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "이메일 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                } if(userPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "비밀번호 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if email id is valid or not
                if (!isEmailValid(userID)){
                    Toast.makeText(getApplicationContext(),
                            "아이디가 이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response: ", response);
                        try {
                            Log.d("Login Test", response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            //성공
                            if(success)
                            {
                                String userID = jsonObject.getString("userID");
                                String userName = jsonObject.getString("userName");
                                String ui = jsonObject.getString("ui");
                                String background = jsonObject.getString("background");
                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                                PreferenceManager.setString(mContext, "Id", userID);
                                PreferenceManager.setString(mContext, "userName", userName);
                                PreferenceManager.setString(mContext, "edit_lockscreen", ui);
                                PreferenceManager.setString(mContext, "edit_background", background);
                                startActivity(intent);
                                finish();
                            }
                            //실패
                            else
                            {
                                Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                try {
                    Log.d("INTERNET: ", "http://" + AppNetwork.getServerIp(getApplicationContext()) + "/data.jsp");
                    LoginRequest loginRequest = new LoginRequest(userID, Encryption.SHA1(userPass), responseListener, getApplicationContext());
                    RequestQueue queue = Volley.newRequestQueue(SigninActivity.this);
                    queue.add(loginRequest);
                } catch (IOException e) {
                    Log.d("LoginReq: ", e.getMessage());
                }
            }
        });
    }

    public void ChangeToSignUp(View view) {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

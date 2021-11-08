package com.example.lockpocket;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.lockpocket.request.RegisterRequest;
import com.example.lockpocket.utils.Encryption;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SignupActivity extends AppCompatActivity {

    EditText et_email;
    EditText et_pass;
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et_email = findViewById(R.id.emailAddress);
        et_pass = findViewById(R.id.password);
        et_name = findViewById(R.id.nickname);
        View password_toggle = findViewById(R.id.password_toggle);
        Log.d("Encryption test", Encryption.SHA1("1234"));
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
    }

    public void CreateAccount(View view) throws IOException {
        String userID = et_email.getText().toString();
        String userPass = et_pass.getText().toString();
        String userName = et_name.getText().toString();

        // Check if email id is valid or not
        if (!isEmailValid(userID)){
            Toast.makeText(getApplicationContext(),
                    "아이디가 이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
            return;
        }

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Register", response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    //성공
                    if(success)
                    {
                        Toast.makeText(getApplicationContext(), "회원등록에 성공하였습니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    //실패
                    else
                    {
                        Toast.makeText(getApplicationContext(), "회원등록에 실패하였습니다", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        // 서버로 볼리를 이용해서 요청한다.
        RegisterRequest registerRequest = new RegisterRequest(userID, Encryption.SHA1(userPass), userName, responseListener, getApplicationContext());
        RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
        queue.add(registerRequest);
    }

    public void BackToSignIn(View view) {
        finish();
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

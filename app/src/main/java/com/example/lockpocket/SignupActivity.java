package com.example.lockpocket;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.lockpocket.utils.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SignupActivity extends AppCompatActivity {

    EditText et_email;
    EditText et_pass;
    EditText et_name;
    EditText et_passCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et_email = findViewById(R.id.emailAddress);
        et_pass = findViewById(R.id.password);
        et_name = findViewById(R.id.nickname);
        et_passCheck = findViewById(R.id.password_check);
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
    }

    public void CreateAccount(View view) throws IOException {
        String userID = et_email.getText().toString();
        String userPass = et_pass.getText().toString();
        String userName = et_name.getText().toString();
        String userPassCheck = et_pass.getText().toString();

        if(userID.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "이메일 항목을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        } if(userPass.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "비밀번호 항목을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        } if(userName.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "별명 항목을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        } if(userPassCheck.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "비밀번호 확인 항목을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if email id is valid or not
        if (!isEmailValid(userID)){
            Toast.makeText(getApplicationContext(),
                    "아이디가 이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show();
            return;
        }
        switch(passwordValidation(userPass, userPassCheck)) {
            case -1:
                Toast.makeText(getApplicationContext(),
                        "비밀번호와 비밀번호 확인에 입력된 내용이 다릅니다", Toast.LENGTH_SHORT).show();
                return;
            case 1:
                break;

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
        String userID = et_email.getText().toString();
        String userPass = et_pass.getText().toString();
        String userName = et_name.getText().toString();
        String userPassCheck = et_pass.getText().toString();
        if(userID.isEmpty() && userPass.isEmpty() && userName.isEmpty() && userPassCheck.isEmpty()) {
            finish();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setMessage("입력한 모든 내용이 지워집니다. \n정말 로그인 화면으로 돌아가시겠습니까?")
                .setTitle("주의")
                .setPositiveButton("돌아가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing to do
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    int passwordValidation(CharSequence passwd, CharSequence check) {
        if(!passwd.equals(check)) {
            Log.e("password error: ", "typed password is different with password check text.");
            return -1;
        }
        return 1;
    }
}

package com.example.lockpocket;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DownloadActivity extends AppCompatActivity {
    private Context mContext;
    EditText et_email;
    EditText et_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button btn_submit = findViewById(R.id.btn_submit);
        TextView tv_id = findViewById(R.id.ui_id);
        TextView tv_info = findViewById(R.id.ui_info);
        TextView tv_ui = findViewById(R.id.ui_ui);

        btn_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response: ", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            //성공
                            if(success)
                            {
                                String ui = jsonObject.getString("ui");
                                String info = jsonObject.getString("info");
                                tv_id.setText("1");
                                tv_info.setText(info);
                                tv_ui.setText(ui);
                            }
                            //실패
                            else
                            {
                                Toast.makeText(getApplicationContext(), "로드에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                try {
                    Log.d("INTERNET: ", "http://192.168.0.12:8080/ex01.jsp");
                    DownloadRequest testrequest = new DownloadRequest("1", responseListener, getApplicationContext());
                    RequestQueue queue = Volley.newRequestQueue(DownloadActivity.this);
                    queue.add(testrequest);

                } catch (IOException e) {
                    Log.d("textQue: ", e.getMessage());
                }
            }
        });
    }
}

package com.example.lockpocket.account;

import android.widget.Toast;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{

    // 서버 URL 설정 (php 파일 연동)
//    final static private String URL = "http://qqa12345.dothome.co.kr/Login.php";
    final static private String URL = "http://115.20.144.64/data.jsp";
    private Map<String, String> map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener, Context context)
    {
        super(Method.POST, URL, listener, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();

            }
        });

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("accessType", "login");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

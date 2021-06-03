package com.example.lockpocket.account;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    // 서버 URL 설정 (php 파일 연동)
//    final static private String URL = "http://qqa12345.dothome.co.kr/Register.php";
    final static private String URL = "http://115.20.144.64/data.jsp";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("userAge", userAge+"");
        map.put("accessType", "join");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

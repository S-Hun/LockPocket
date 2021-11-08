package com.example.lockpocket.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.lockpocket.utils.AppNetwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, String userName, Response.Listener<String> listener, Context context) throws IOException {
        super(Method.POST,
                "http://" + AppNetwork.getServerIp(context) + "/sign.jsp", listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("accessType", "join");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

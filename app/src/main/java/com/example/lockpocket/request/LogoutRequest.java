package com.example.lockpocket.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.lockpocket.utils.AppNetwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogoutRequest extends StringRequest {
    private Map<String, String> map;

    public LogoutRequest(String id, String ui, String bg, Response.Listener<String> listener, Context context) throws IOException {
        super(Request.Method.POST, "http://" + AppNetwork.getServerIp(context) + "/sign.jsp", listener, null);

        map = new HashMap<>();
        map.put("userID", id);
        map.put("ui", ui);
        map.put("bg", bg);
        map.put("accessType", "logout");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

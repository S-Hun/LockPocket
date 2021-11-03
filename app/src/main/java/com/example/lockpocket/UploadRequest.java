package com.example.lockpocket;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.lockpocket.utils.AppNetwork;

import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class UploadRequest extends StringRequest{
    private Map<String, String> map;

    public UploadRequest(String info, String ui, Response.Listener<String> listener, Context context) throws IOException {
        super(Method.POST,
                "http://172.30.2.65:8080/ex01.jsp", listener, null);

        map = new HashMap<>();
        map.put("info", info);
        map.put("ui", ui);
        map.put("accessType", "upload");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

package com.example.lockpocket;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.lockpocket.utils.AppNetwork;
import com.example.lockpocket.utils.PreferenceManager;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UploadRequest extends StringRequest{
    private Map<String, String> map;
    public UploadRequest(String id, String up, String ui, String bg, Response.Listener<String> listener, Context context) throws IOException {
        super(Method.POST,
                "http://" + AppNetwork.getServerIp(context) + "/connect.jsp", listener, null);


        map = new HashMap<>();
        map.put("id", id);
        map.put("upload", up);
        map.put("ui", ui);
        map.put("bg", bg);
        map.put("accessType", "upload");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

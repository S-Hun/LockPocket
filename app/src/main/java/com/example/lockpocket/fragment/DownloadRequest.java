package com.example.lockpocket.fragment;

import android.util.Log;
import android.widget.Toast;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lockpocket.utils.AppNetwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DownloadRequest extends StringRequest {

    // final static private String URL;
    private Map<String, String> map;

    public DownloadRequest(Response.Listener<String> listener, Context context) throws IOException {
        super(Method.POST,
                "http://" + AppNetwork.getServerIp(context) + "/connect.jsp", listener, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        map = new HashMap<>();
        map.put("accessType", "download");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

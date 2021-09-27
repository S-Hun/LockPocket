package com.example.lockpocket.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppNetwork {
    public static String getServerIp(Context context) {
        try {
            Properties properties = new Properties();
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("network.properties");
            properties.load(inputStream);
            return properties.getProperty("server_ip");
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return null;
    }
}

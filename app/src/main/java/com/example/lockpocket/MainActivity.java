package com.example.lockpocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    /*
//    hardkgosai by stackoverflow(2021-06-27)
//    https://stackoverflow.com/questions/21724420/how-to-hide-navigation-bar-permanently-in-android-activity
//     */
//    public void hideSystemUI(Window window) { //pass getWindow();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//
//            window.getInsetsController().hide(WindowInsets.Type.systemBars());
//
//        } else {
//
//            View decorView = window.getDecorView();
//
//            int uiVisibility = decorView.getSystemUiVisibility();
//
//            uiVisibility |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
//            uiVisibility |= View.SYSTEM_UI_FLAG_FULLSCREEN;
//            uiVisibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                uiVisibility |= View.SYSTEM_UI_FLAG_IMMERSIVE;
//                uiVisibility |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            }
//
//            decorView.setSystemUiVisibility(uiVisibility);
//        }
//    }
}
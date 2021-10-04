package com.example.lockpocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lockpocket.lockmethod.UnlockBar;

public class LockScreenActivity extends AppCompatActivity {
    private static View[] pos;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    RelativeLayout layout;
    String UI;

    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lockscreen);
        UnlockBar unlock = (UnlockBar)findViewById(R.id.unlock);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();
        layout = findViewById(R.id.activity_lockscreen);

        /*
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        final int new_flags = Window.FEATURE_ACTION_BAR_OVERLAY;
        final int inset_flags = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE;
        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);
            getWindow().getInsetsController().setSystemBarsBehavior(inset_flags);
            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

        */

        pos = new View[]{
                findViewById(R.id.pos1),
                findViewById(R.id.pos2),
                findViewById(R.id.pos3),
                findViewById(R.id.pos4),
                findViewById(R.id.pos5),
                findViewById(R.id.pos6),
                findViewById(R.id.pos7),
                findViewById(R.id.pos8),
                findViewById(R.id.pos9),
                findViewById(R.id.pos10),
                findViewById(R.id.pos11),
                findViewById(R.id.pos12)
        };

        UI = pref.getString("UI", "ZZZZZZZZZZZZ");
        if(UI.length() < 12) UI = "ZZZZZZZZZZZZ";

        for(int i=0; i<UI.length(); i++){
            if(UI.charAt(i)=='A') pos[i].setBackgroundColor(getResources().getColor(R.color.black));
            else if(UI.charAt(i)=='B') pos[i].setBackgroundColor(getResources().getColor(R.color.black));
            else if(UI.charAt(i)=='C') pos[i].setBackgroundColor(getResources().getColor(R.color.black));
            else {
                pos[i].setBackgroundColor(getResources().getColor(R.color.white));
            }
        }

        // Attach listener
        unlock.setOnUnlockListenerRight(new UnlockBar.OnUnlockListener() {
            @Override
            public void onUnlock()
            {
                Toast.makeText(LockScreenActivity.this, "Right Action", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        unlock.setOnUnlockListenerLeft(new UnlockBar.OnUnlockListener() {
            @Override
            public void onUnlock()
            {
                Toast.makeText(LockScreenActivity.this, "Left Action", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    @Override
    public void onAttachedToWindow() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
//                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onAttachedToWindow();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((LockApplication) getApplication()).lockScreenShow = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((LockApplication) getApplication()).lockScreenShow = false;
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
}

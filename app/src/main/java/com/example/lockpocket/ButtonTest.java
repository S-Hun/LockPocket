package com.example.lockpocket;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ButtonTest extends AppCompatActivity {
    ProgressHandler handler;
    Button buttonWifi;
    WifiManager wifiManager;
    TextView Date;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String time = sdf.format(new Date(System.currentTimeMillis()));
    Calendar cal = new GregorianCalendar();
    String timeGre = String.format("%d/%d/%d %d:%d:%d",
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_WEEK),
            cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
    private static CameraManager mCameraManager;
    private static ImageButton mImageButtonFlashOnOff;
    private Button buttonSetting;
    private static boolean mFlashOn = false;
    private String mCameraId;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_test);
//        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
//            Toast.makeText(getApplicationContext(), "This device is not support camera flash \n The app will be terminated", Toast.LENGTH_LONG).show();
//            delayedFinish();
//            return;
//        }
        // 시계
        Date = (TextView) findViewById(R.id.time);
        handler = new ProgressHandler();
        runTime();
        //비행기모드
        buttonSetting = (Button)findViewById(R.id.airPlaneBtn);
        buttonSetting.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);

                startActivityForResult(intent, 0);

            }

        });


        출처: https://yeolco.tistory.com/99 [열코의 프로그래밍 일기]
        // button turn on off(플래쉬)
        mImageButtonFlashOnOff = findViewById(R.id.ibFlashOnoff);

//        mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
//        mImageButtonFlashOnOff.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                flashlight();
//                mImageButtonFlashOnOff.setImageResource(mFlashOn ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);
//            }
//        });
        buttonWifi = findViewById(R.id.wifiBtn);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if(wifiManager.isWifiEnabled()){
            buttonWifi.setText("Turn Wifi OFF");
        }
        else if(!wifiManager.isWifiEnabled()){
            buttonWifi.setText("Turn Wifi ON");
        }
        buttonWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT<Build.VERSION_CODES.Q)
                    wifiManager.setWifiEnabled(true);
                else
                {
                    Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                    startActivityForResult(panelIntent,1);
                }

            }
        });
    }
    public void runTime(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        time = sdf.format(new Date(System.currentTimeMillis()));

                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);

                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ex){

                    }
                }
            }
        });
        thread.start();
    }
    class ProgressHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Date.setText(time);
        }
    }
//        private void delayedFinish(){
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    finish();
//                }
//            }, 3500);
//        }
//        @RequiresApi(api = Build.VERSION_CODES.M)
//        void flashlight()
//        {
//            if (mCameraId == null) {
//                try {
//                    for (String id : mCameraManager.getCameraIdList()) {
//                        CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
//                        Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
//                        Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
//                        if (flashAvailable != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
//                            mCameraId = id;
//                            break;
//                        }
//                    }
//
//                } catch (CameraAccessException e) {
//                    mCameraId = null;
//                    e.printStackTrace();
//                    return;
//                }
//            }
//            mFlashOn = !mFlashOn;
//            try {
//                mCameraManager.setTorchMode(mCameraId, mFlashOn);
//            }
//            catch (CameraAccessException e)
//            {
//                e.printStackTrace();
//            }
//        }



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
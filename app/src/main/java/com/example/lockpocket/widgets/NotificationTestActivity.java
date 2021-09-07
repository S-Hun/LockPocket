package com.example.lockpocket.widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NotificationTestActivity extends AppCompatActivity implements CustomNotificationListener {
    private TextView txtView ;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_notification_test) ; // Object Board (1)
        new NotificationService().setListener(this) ;
        // txtView = findViewById(R.id.textView) ; // Result Board (2)
        /* Notification Creator */
//        Button btnCreateNotification = findViewById(R.id. btnCreateNotification ) ;
//        btnCreateNotification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v) {
//                NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
//                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity. this, default_notification_channel_id ) ;
//                mBuilder.setContentTitle( "My Notification" ) ;
//                mBuilder.setContentText( "Notification Listener Service Example" ) ;
//                mBuilder.setTicker( "Notification Listener Service Example" ) ;
//                mBuilder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
//                mBuilder.setAutoCancel( true ) ;
//                if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
//                    int importance = NotificationManager. IMPORTANCE_HIGH ;
//                    NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
//                    mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
//                    assert mNotificationManager != null;
//                    mNotificationManager.createNotificationChannel(notificationChannel) ;
//                }
//                assert mNotificationManager != null;
//                mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;
//            }
//        }) ;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // getMenuInflater().inflate(R.menu.menu_main , menu) ; //Menu Resource, Menu -> PERMISSION ACCESS MENU INFLATER
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_settings :
//                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS" ) ; // NEED PERMISSION
//                startActivity(intent) ;
//                return true;
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }
    @Override
    public void setValue (String packageName) {
        txtView.append( " \n " + packageName) ;
    }
}
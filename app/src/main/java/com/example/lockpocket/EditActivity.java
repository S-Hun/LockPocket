package com.example.lockpocket;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lockpocket.utils.LockScreen;

public class EditActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static String[] CompTag;
    private static ImageView[] Comp;
    private static int[] posTag;
    private static View[] pos;
    private char[] posComp;

    String UI;

    // Main
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);

        // LockScreen 싱글턴 패턴으로 호출 (홈버튼 비활성화 true 로)
        LockScreen.getInstance().init(this,true);

        // LockScreen 의 활성화 상태()로 토글 버튼 상태 조정
        if(LockScreen.getInstance().isActive()){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);

        }

        // 토글버튼의 변경 리스너
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                // On Toggle 일 때, active 반대로 deactive
                if(checked){

                    LockScreen.getInstance().active();
                }else{
                    LockScreen.getInstance().deactivate();
                }
            }
        });

        // Drag
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        UI = pref.getString("UI", "ZZZZZZZZZZZZ");
        if(UI.length() < 12) UI = "ZZZZZZZZZZZZ";
        posComp = UI.toCharArray();
        Log.d("ColorLoad", UI);

        Comp = new ImageView[]{
                (ImageView) findViewById(R.id.red),
                (ImageView) findViewById(R.id.blue),
                (ImageView) findViewById(R.id.green)
        };

        CompTag = new String[]{
                "RED", "BLUE", "GREEN"
        };

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

        posTag = new int[]{
                0,1,2,3,4,5,6,7,8,9,10,11
        };

        setColor();

        for(int i=0; i<Comp.length; i++){
            Comp[i].setTag(CompTag[i]);
            Comp[i].setOnLongClickListener(new LongClickListener());
        }

        for(int i=0; i<pos.length; i++) {
            pos[i].setTag(posTag[i]);
            pos[i].setOnDragListener(new DragListener());
            pos[i].setOnClickListener(new ClickListener());
        }
        findViewById(R.id.savebtn).setOnClickListener(new ClickListener());
    }

    private final class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.savebtn:
                    saveColor();
                    break;
                default:
                    if(posComp[(int)v.getTag()] != 'Z'){
                        posComp[(int)v.getTag()] = 'Z';
                        v.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                    break;
            }
        }
    }

    private final class LongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            ClipData.Item item = new ClipData.Item(
                    (CharSequence) v.getTag()
            );
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(v.getTag().toString(),
                    mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

            v.startDrag(data,
                    shadowBuilder,
                    v,
                    0);

            return true;
        }
    }

    class DragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch(event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.d("DragClickListener", "ACTION_DRAG_STARTED");
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("DragClickListener", "ACTION_DRAG_ENTERED");
                    // v.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d("DragClickListener", "ACTION_DRAG_EXITED");
                    // v.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    break;

                case DragEvent.ACTION_DROP:
                    Log.d("DragClickListener", "ACTION_DROP");

                    View view = (View)event.getLocalState();
                    if(((String) view.getTag()).equals("RED")){
                        v.setBackgroundColor(getResources().getColor(R.color.red));
                        posComp[(int)v.getTag()] = 'A';
                    }
                    else if(((String) view.getTag()).equals("BLUE")){
                        v.setBackgroundColor(getResources().getColor(R.color.blue));
                        posComp[(int)v.getTag()] = 'B';
                    }
                    else if(((String) view.getTag()).equals("GREEN")){
                        v.setBackgroundColor(getResources().getColor(R.color.green));
                        posComp[(int)v.getTag()] = 'C';
                    }
                    /*
                    if (v == findViewById(R.id.pos1)){
                        View view = (View)event.getLocalState();
                        if(((String) view.getTag()).equals("DRAG IMAGE")){
                            Log.d("DragClickListener", "VALID_PASS");
                        }
                        TextView text = (TextView)findViewById(R.id.textView);
                        text.setText("Image Dropped");
                    } else {
                        View view = (View)event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Context context = getApplicationContext();
                        Toast.makeText(context,
                                "You can't drop image here.",
                                Toast.LENGTH_LONG).show();
                    }
                    */
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("DragClickListener", "ACTION_DRAG_ENDED");

                default:
                    break;
            }
            return true;
        }
    }

    private boolean setColor(){
        if(UI == null) return false;
        for(int i=0; i<UI.length(); i++){
            if(UI.charAt(i)=='A') pos[i].setBackgroundColor(getResources().getColor(R.color.red));
            else if(UI.charAt(i)=='B') pos[i].setBackgroundColor(getResources().getColor(R.color.blue));
            else if(UI.charAt(i)=='C') pos[i].setBackgroundColor(getResources().getColor(R.color.green));
            else {
                pos[i].setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
        return true;
    }
    private boolean saveColor(){
        editor.putString("UI", String.valueOf(posComp));
        editor.apply();
        return false;
    }
}

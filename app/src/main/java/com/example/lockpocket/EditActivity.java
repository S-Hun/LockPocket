package com.example.lockpocket;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static List<String> CompTag;
    private static List<View> Comp;
    private static List<Integer> posTag;
    private static List<View> pos;
    private char[] posComp;

    String UI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        View btn1 = (ImageView)findViewById(R.id.menu_load);
        View btn2 = (ImageView)findViewById(R.id.menu_close);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.menu_bar).setVisibility(View.VISIBLE);
                btn1.setVisibility(View.INVISIBLE);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.menu_bar).setVisibility(View.INVISIBLE);
                btn1.setVisibility(View.VISIBLE);
            }
        });

        // Drag
//        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
//        editor = pref.edit();
//
//        UI = pref.getString("UI", "ZZZZZZZZZZZZ");
//        if(UI.length() < 12) UI = "ZZZZZZZZZZZZ";
//        posComp = UI.toCharArray();
//        Log.d("ColorLoad", UI);

        Comp = new ArrayList<View>();
        Comp.add((View) findViewById(R.id.w_flashlight));
        Comp.add((View) findViewById(R.id.w_43_notification));

        CompTag = new ArrayList<>();
        CompTag.add("flashlight");
        CompTag.add("43_notification");

        pos = new ArrayList<View>();
        add_view_child(pos, (LinearLayout)findViewById(R.id.line1));
        add_view_child(pos, (LinearLayout)findViewById(R.id.line2));
        add_view_child(pos, (LinearLayout)findViewById(R.id.line3));
        add_view_child(pos, (LinearLayout)findViewById(R.id.line4));

        posTag = new ArrayList<>();
        for(int i=0; i<30; i++) posTag.add(i);

//        setColor();

        for(int i=0; i<Comp.size(); i++){
            Comp.get(i).setTag(CompTag.get(i));
            Comp.get(i).setOnLongClickListener(new LongClickListener());
        }

        for(int i=0; i<pos.size(); i++) {
            pos.get(i).setTag(posTag.get(i));
            pos.get(i).setOnDragListener(new DragListener());
            pos.get(i).setOnClickListener(new ClickListener());
        }
//        findViewById(R.id.savebtn).setOnClickListener(new ClickListener());
    }

    void add_view_child(List list, ViewGroup object){
        int count = object.getChildCount();
        for(int i=0; i<count; i++){
            list.add(object.getChildAt(i));
        }
    }

    private final class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
//                case R.id.savebtn:
//                    saveColor();
//                    break;
                default:
                    if(posComp[(int)v.getTag()] != 'Z'){
                        posComp[(int)v.getTag()] = 'Z';
                        v.setBackground(null);
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
            View tv = findViewById(R.id.w_43_text_box);
            View.DragShadowBuilder shadowBuilder;
            if(v != findViewById(R.id.w_43_notification)) shadowBuilder = new View.DragShadowBuilder(v);
            else shadowBuilder = new View.DragShadowBuilder(tv);

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

//                    View view = (View)event.getLocalState();
//                    if(((String) view.getTag()).equals("RED")){
//                        v.setBackground(getDrawable(R.drawable.background_red));
//                        posComp[(int)v.getTag()] = 'A';
//                    }
//                    else if(((String) view.getTag()).equals("BLUE")){
//                        v.setBackground(getDrawable(R.drawable.background_blue));
//                        posComp[(int)v.getTag()] = 'B';
//                    }
//                    else if(((String) view.getTag()).equals("GREEN")){
//                        v.setBackground(getDrawable(R.drawable.background_green));
//                        posComp[(int)v.getTag()] = 'C';
//                    }
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("DragClickListener", "ACTION_DRAG_ENDED");

                default:
                    break;
            }
            return true;
        }
    }
}
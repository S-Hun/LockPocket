package com.example.lockpocket;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lockpocket.utils.BitmapConverter;
import com.example.lockpocket.utils.CustomDragShadowBuilder;
import com.example.lockpocket.utils.GridLock46;
import com.example.lockpocket.utils.LockTable;
import com.example.lockpocket.utils.PreferenceManager;
import com.example.lockpocket.utils.WidgetList;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    static final String TAG = "EDIT";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    ViewGroup mainLayout;
    ViewGroup lockTableLayout;
    LockTable lockTableObject;
    ImageView IV_background;
    ImageView IV_remove;
    Animation appear_remove_anim;
    Animation disappear_remove_anim;
    ArrayList<ViewGroup> widgetFrame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        IV_background = findViewById(R.id.background);
        ScreenSetup();

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_main);
        mainLayout = findViewById(R.id.main);
        IV_remove = findViewById(R.id.remove_place);
        appear_remove_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.appear_remove_space);
        disappear_remove_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.disappear_remove_space);
        LockTableSetup();

        navigationView = findViewById(R.id.nav_view);
        widgetFrame = new ArrayList<>();
        MenuSetup();
        WidgetSetup();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        printViewHierarchy(navigationView, "");
        ViewGroup navigationMenuView = (ViewGroup)navigationView.getChildAt(0);
        ViewGroup navigationMenuItemView;

        navigationMenuItemView = (ViewGroup)navigationMenuView.getChildAt(1);
        View back = navigationMenuItemView.getChildAt(0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return false;
            }
        });
        for(int i=0; i<lockTableObject.tableWidget.length; i++) {
            navigationMenuItemView = (ViewGroup)navigationMenuView.getChildAt(i + 2);
            View v = navigationMenuItemView.getChildAt(0);
            TextView tv = (TextView) navigationMenuItemView.getChildAt(0);
            tv.setSingleLine();
            tv.setMarqueeRepeatLimit(-1);
            tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            tv.setSelected(true);

            v.setTag(R.string.role, "widget");
            v.setTag(R.string.role_describe, lockTableObject.tableWidget[i]);
            v.setTag(WidgetList.getName(lockTableObject.tableWidget[i]));
            v.setOnLongClickListener(new MenuLongClickListener());
            v.setOnDragListener(new MenuDragListener());
        }

        return super.onPrepareOptionsMenu(menu);
    }

    private final class MenuLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
            ClipData.Item item = new ClipData.Item(
                    (CharSequence) v.getTag()
            );
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);

            if(v.getTag(R.string.role) == "widget") {
                ViewGroup vg = widgetFrame.get((Integer) v.getTag(R.string.role_describe));
                CustomDragShadowBuilder shadowBuilder = new CustomDragShadowBuilder(vg);
                v.startDragAndDrop(data,
                        shadowBuilder,
                        v,
                        0);
            } else if (v.getTag(R.string.role) == "placed_widget") {
                v.setVisibility(View.INVISIBLE);
                CustomDragShadowBuilder shadowBuilder = new CustomDragShadowBuilder(v);
                v.startDragAndDrop(data,
                        shadowBuilder,
                        v,
                        0);
            }

            return true;
        }
    }

    private final class MenuDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View placedView, DragEvent event) {
            View draggedView = (View) event.getLocalState();
            switch(event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    IV_remove.startAnimation(appear_remove_anim);
                    Log.d("DragClickListener", "ACTION_DRAG_STARTED");
                    break;

                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("DragClickListener", "ACTION_DRAG_ENTERED");
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d("DragClickListener", "ACTION_DRAG_EXITED");
                    break;

                case DragEvent.ACTION_DROP:
                    if(draggedView.getTag(R.string.role) == "placed_widget" && placedView.getTag(R.string.role) == "remove") {
                        draggedView.setVisibility(View.VISIBLE);
                        lockTableObject.removeWidget(draggedView.getId());
                        break;
                    }
                    int place_num = (int) placedView.getTag(R.string.role_describe);
                    int type = (int) draggedView.getTag(R.string.role_describe);
                    int id = draggedView.getId();
                    if(lockTableObject.checkPosition(new Point(place_num%4, place_num/4), new Size(WidgetList.getId(type).w, WidgetList.getId(type).h), id)) { // Move placed widget // original : placedView.getTag(R.string.role) == "place"
                        if(draggedView.getTag(R.string.role) == "widget") {
                            int W = placedView.getWidth();
                            int H = placedView.getHeight();
                            lockTableObject.insertWidget(place_num%4, place_num/4, type, new Size(W, H));
                        } else if (draggedView.getTag(R.string.role) == "placed_widget") {
                            lockTableObject.clearPosition(id, 0);
                            lockTableObject.setUpPosition(new Point(place_num%4, place_num/4), new Size(WidgetList.getId(type).w, WidgetList.getId(type).h), id);
                            View v_parent = (View) placedView.getParent();
                            float x = placedView.getX();
                            float y = v_parent.getY();
                            RelativeLayout.LayoutParams objParams = (RelativeLayout.LayoutParams) draggedView.getLayoutParams();
                            objParams.leftMargin = (int)x;
                            objParams.topMargin = (int)y;
                            draggedView.setLayoutParams(objParams);
                            draggedView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Log.d("Sys: ", "이동 불가능한 위치");
                        if(draggedView.getTag(R.string.role) == "widget") {
                            Toast.makeText(getApplicationContext(), "배치 불가능한 위치입니다", Toast.LENGTH_SHORT).show();
                        } else if (draggedView.getTag(R.string.role) == "placed_widget") {
                            Toast.makeText(getApplicationContext(), "이동 불가능한 위치입니다", Toast.LENGTH_SHORT).show();
                            draggedView.setVisibility(View.VISIBLE);
                        }
                    }
                    Log.d("DragClickListener", "ACTION_DROP");
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    if(draggedView.getVisibility() == View.INVISIBLE) {
                        Toast.makeText(getApplicationContext(), "화면을 벗어났습니다", Toast.LENGTH_SHORT).show();
                        draggedView.setVisibility(View.VISIBLE);
                    }
                    IV_remove.startAnimation(disappear_remove_anim);
                    Log.d("DragClickListener", "ACTION_DRAG_ENDED");

                default:
                    break;
            }
            return true;
        }
    }

    void ScreenSetup() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        String backgroundBitmapString = PreferenceManager.getString(getApplicationContext(), "edit_background");
        if(!backgroundBitmapString.equals("")) {
            Bitmap bitmap = BitmapConverter.StringToBitmap(backgroundBitmapString);
            IV_background.setImageBitmap(bitmap);
        }
    }

    public void LockTableSetup() {
        int height = (int) (getWindowManager().getDefaultDisplay().getHeight() * (2.3/3));
        String tableType = "grid46";
        if(tableType.equals("grid46")) {
            // tableParams
            LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tableParams.weight = (float) 0.08;
            final int tbMg = 32;
            tableParams.setMargins(tbMg, tbMg * 2, tbMg, tbMg); // ltrb

            // viewParams
            LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(0, height/6);
            viewParams.weight = 1;

            // create Table
            RelativeLayout tableLayout = new RelativeLayout(getApplicationContext());
            tableLayout.setLayoutParams(tableParams);
            tableLayout.setId(View.generateViewId());

            LinearLayout[] tableRowList = new LinearLayout[6];

            for(int i=0; i<6; i++) {
                // create Row
                tableRowList[i] = new LinearLayout(getApplicationContext());
                tableRowList[i].setId(View.generateViewId());

                // tableRowParams
                RelativeLayout.LayoutParams rowParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                if(i > 0) rowParams.addRule(RelativeLayout.BELOW, tableRowList[i-1].getId());
                tableRowList[i].setLayoutParams(rowParams);
                tableRowList[i].setOrientation(LinearLayout.HORIZONTAL);
                for(int j=0; j<4; j++){
                    // create View
                    View newView = new View(getApplicationContext());
                    newView.setId(View.generateViewId());
                    newView.setTag(R.string.role, "place");
                    newView.setTag(R.string.role_describe, 4*i+j);
                    newView.setOnDragListener(new MenuDragListener());
                    newView.setLayoutParams(viewParams);
                    newView.setBackgroundResource(R.drawable.bg_place_view);
                    tableRowList[i].addView(newView, tableRowList[i].getChildCount());
                }
                tableLayout.addView(tableRowList[i], tableLayout.getChildCount());
            }
            mainLayout.addView(tableLayout, 0);
            lockTableLayout = tableLayout;
            lockTableObject = new GridLock46(getApplicationContext(), lockTableLayout);
        }
        String template = PreferenceManager.getString(getApplicationContext(), "edit_lockscreen");
        if(!template.equals("")) {
            Log.d("Loaded template", template);
            ViewGroup v1 = (ViewGroup) lockTableLayout.getChildAt(0);
            View v2 = v1.getChildAt(0);
            v2.post(new Runnable() {
                @Override
                public void run() {
                    Point p = new Point(v2.getWidth(), v2.getHeight());
                    lockTableObject.stringToTable(template, new Size(p.x, p.y));
                }
            });
        }
        IV_remove.setTag(R.string.role, "remove");
        IV_remove.setOnDragListener(new MenuDragListener());
        IV_remove.startAnimation(disappear_remove_anim);
    }

    public void MenuSetup() {
        Menu menu = navigationView.getMenu();
        menu.add(Menu.NONE, Menu.NONE, Menu.NONE, "닫기");
        menu.getItem(0).setIcon(R.drawable.ic_close);
        for(int i=0; i<lockTableObject.tableWidget.length; i++) {
            menu.add(Menu.NONE, Menu.NONE, Menu.NONE, WidgetList.getName(lockTableObject.tableWidget[i]));
            menu.getItem(i+1).setIcon(WidgetList.getId(lockTableObject.tableWidget[i]).icon);
        }

        navigationView.post(new Runnable() {
            @Override
            public void run() {
                onPrepareOptionsMenu(menu);
            }
        });
    }

    public void WidgetSetup() {
        ViewGroup v1 = (ViewGroup) lockTableLayout.getChildAt(0);
        View v2 = v1.getChildAt(0);
        v2.post(new Runnable() {
            @Override
            public void run() {
                Point p = new Point(v2.getWidth(), v2.getHeight());
                for(int i=0; i<=8; i++) {
                    ViewGroup vg = lockTableObject.getViewGroup(i, p);
                    widgetFrame.add(vg);
                    vg.setVisibility(View.INVISIBLE);
                    lockTableLayout.addView(vg);
                }
            }
        });
    }

    public static void printViewHierarchy(ViewGroup vg, String prefix) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View v = vg.getChildAt(i);
            String desc = prefix + " | " + "[" + i + "/" + (vg.getChildCount()-1) + "] "+ v.getClass().getSimpleName() + " " + v.getId();
            Log.v("x", desc);

            if (v instanceof ViewGroup) {
                printViewHierarchy((ViewGroup)v, desc);
            }
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("종료")
                .setMessage("편집화면을 나가고 저장하겠습니까?")
                .setPositiveButton("저장 후 종료", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String saveData = lockTableObject.tableToString();
                        if(saveData != null) PreferenceManager.setString(getApplicationContext(), "edit_lockscreen", saveData);
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), TemplateActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }).setNeutralButton("저장하지 않고 종료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), TemplateActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
    }
}
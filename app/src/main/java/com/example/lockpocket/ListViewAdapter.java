package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lockpocket.utils.BitmapConverter;
import com.example.lockpocket.utils.PreferenceManager;
import com.example.lockpocket.utils.TableFloater;

import java.util.List;

/**
 * Created by Administrator on 2016-10-13.
 */
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private List<String> ui;
    private List<String> date;
    private List<String> id;
    private List<String> bg;
    Context mcontext;
    public ListViewAdapter(Context context, List<String> ui, List<String> date, List<String> id, List<String> bg){
        // MainActivity 에서 데이터 리스트를 넘겨 받는다.
        this.ui = ui;
        this.date = date;
        this.id = id;
        this.bg = bg;
        this.inflate = LayoutInflater.from(context);
        mcontext = context;
    }
    @Override
    public int getCount() {
        return ui.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.row_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.who_id);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.pre = (FrameLayout) convertView.findViewById(R.id.view_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CommunityDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", id.get(position));
                intent.putExtra("date", date.get(position));
                intent.putExtra("ui", ui.get(position));
                intent.putExtra("bg", bg.get(position));
                v.getContext().startActivity(intent);
            }
        });

        viewHolder.id.setText(id.get(position).split("@")[0]);
        viewHolder.date.setText(date.get(position));
        String lock = ui.get(position);
        previewCommunity(lock, bg.get(position));
        return convertView;
    }

    class ViewHolder{
        public TextView id;
        public TextView date;
        public ViewGroup pre;
    }

    public void previewCommunity(String lock, String background){
        DisplayMetrics metrics = mcontext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels / 3;
        int height = metrics.heightPixels / 3;
        TableFloater tf = new TableFloater(mcontext, lock);
        ViewGroup vg = tf.template(new Point(width, height));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        params.setMarginStart((int) (width / 3.5));
        vg.setLayoutParams(params);
        String backgroundBitmap = background;
        if(!backgroundBitmap.equals("")) {
            Bitmap bitmap = BitmapConverter.StringToBitmap(backgroundBitmap);
            vg.setBackground(new BitmapDrawable(mcontext.getResources(), bitmap));
        }
        viewHolder.pre.addView(vg);
    }
}
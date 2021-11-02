package com.example.lockpocket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016-10-13.
 */
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private List<String> list;
    private Context mcontext;

    public ListViewAdapter(Context context, List<String> list){
        // MainActivity 에서 데이터 리스트를 넘겨 받는다.
        this.list = list;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
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
            viewHolder.label = (TextView) convertView.findViewById(R.id.label);
            viewHolder.creator = (TextView) convertView.findViewById(R.id.creator);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();

        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CommunityDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);


            }
        });

        // 각 셀에 넘겨받은 텍스트 데이터를 넣는다.
        viewHolder.label.setText( list.get(position) );
        viewHolder.creator.setText(list.get(position));
        return convertView;
    }

    class ViewHolder{
        public TextView label;
        public TextView creator;
        public ImageView imageview;
    }
}
package com.example.lockpocket.viewpager2;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockpocket.R;

public class ViewHolderPage extends RecyclerView.ViewHolder {

    private TextView tv_title;
    private ImageView iv_banner;
    private RelativeLayout rl_layout;

    DataPage data;

    public ViewHolderPage(@NonNull View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.tv_title);
        iv_banner = itemView.findViewById(R.id.iv_banner);
        // rl_layout = itemView.findViewById(R.id.rl_layout);
    }

    public void onBind(DataPage data){
        this.data = data;

        tv_title.setText(data.getTitle());
        iv_banner.setImageResource(data.getImage());
        // rl_layout.setBackgroundResource(data.getColor());
    }
}

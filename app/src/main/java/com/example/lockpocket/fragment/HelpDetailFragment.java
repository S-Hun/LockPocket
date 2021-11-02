package com.example.lockpocket.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lockpocket.R;
import com.example.lockpocket.viewpager2.DataPage;
import com.example.lockpocket.viewpager2.ViewPagerAdapter;

import java.util.ArrayList;

public class HelpDetailFragment extends Fragment {

    ViewPager2 viewPager2;
    int[] pic = new int[10];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_detail, container, false);
        viewPager2 = view.findViewById(R.id.viewPager_help);
        Bundle picture = getArguments();
        if(picture != null)
        {
            pic = picture.getIntArray("pictureID");
        }
        ArrayList<DataPage> list = new ArrayList<>();
        for(int i=0; i<pic.length; i++)
        {
            if(pic[i] == 0)
                break;
            list.add(new DataPage(pic[i], ""));
        }

        viewPager2.setAdapter(new ViewPagerAdapter(list));

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }
}
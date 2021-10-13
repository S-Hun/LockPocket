package com.example.lockpocket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lockpocket.R;
import com.example.lockpocket.viewpager2.DataPage;
import com.example.lockpocket.viewpager2.ViewPagerAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager2 = view.findViewById(R.id.viewPager);

        ArrayList<DataPage> list = new ArrayList<>();
        list.add(new DataPage(R.drawable.test_image1,""));
        list.add(new DataPage(R.drawable.test_image2, ""));

        viewPager2.setAdapter(new ViewPagerAdapter(list));

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }
}

package com.example.x_smartcity_s.Fragment.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_smartcity_s.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  13:49
 */
public class Fragment_news_news extends Fragment {

    private String newsid;
    public Fragment_news_news(String newsid) {
        this.newsid = newsid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_news_news,container,false);




        return view;
    }
}

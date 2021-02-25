package com.example.x_smartcity_s.Fragment.Page;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Fragment.Party.Fragment_Party_event;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetAllActions;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;


import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  10:40
 */
public class Fragment_Party_event_msg extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtTitle;
    private TextView txtTime;
    private ImageView image;
    private TextView txtMsg;
    private GetAllActions allActions;

    public Fragment_Party_event_msg(GetAllActions actions) {
        this.allActions = actions;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_party_event_msg, container, false);
        initView(view);
        titleTitle.setText("党员活动");

        getShow();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Party_event());
            }
        });

        return view;
    }

    private void getShow() {
        txtTitle.setText(allActions.getName());
        txtTime.setText("发布时间"+allActions.getTime());
        txtMsg.setText(allActions.getDescription());
        new OkHttpToImage()
                .setUrl(allActions.getImage())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        image.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        txtTitle = view.findViewById(R.id.txt_title);
        txtTime = view.findViewById(R.id.txt_time);
        image = view.findViewById(R.id.image);
        txtMsg = view.findViewById(R.id.txt_msg);
    }
}

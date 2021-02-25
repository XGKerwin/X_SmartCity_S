package com.example.x_smartcity_s.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;

import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  10:49
 */
public class Fragment_xinwen1 extends Fragment {
    private int finalI;
    private String user1;
    private String title;
    private String msg;
    private String img;
    private ImageView back;
    private TextView ABiaoti;
    private TextView xinwenTitle;
    private ImageView xinwenImg;
    private TextView xinwenMsg;
    private int zhuye;

    public Fragment_xinwen1(int finalI) {
        this.finalI = finalI;
    }

    public Fragment_xinwen1(int finalI, int i) {
        this.finalI = finalI;
        this.zhuye = i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_xinwen1, container, false);
        initView(view);
        getOkhttp();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zhuye == 1){
                    getFragment(new Fragment_Page());
                }else {
                    getFragment(new Fragment_news());
                }
            }
        });
        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }


    private void getOkhttp() {
        new OKHttpTo()
                .setUrl("getNewsByImages")
                .setJSONObject("num", finalI)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        title = jsonObject.optString("title");
                        msg = jsonObject.optString("abstract");
                        img = jsonObject.optString("picture");
                        getShow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getShow() {
        xinwenTitle.setText(title);
        xinwenMsg.setText(msg);
        new OkHttpToImage()
                .setUrl(img)
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        xinwenImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        ABiaoti = view.findViewById(R.id.A_biaoti);
        xinwenTitle = view.findViewById(R.id.xinwen_title);
        xinwenImg = view.findViewById(R.id.xinwen_img);
        xinwenMsg = view.findViewById(R.id.xinwen_msg);
    }
}

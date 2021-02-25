package com.example.x_smartcity_s.Fragment.Service;

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
 * date   : 2021/2/22  15:58
 */
public class Fragment_Service_metro_path extends Fragment {

    private ImageView back;
    private TextView xianlu1;
    private TextView xianlu2;
    private TextView xianlu3;
    private TextView xianlu4;
    private TextView txtXianlu;
    private ImageView img;
    private String url = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_metro_path, container, false);
        initView(view);
        getOkHttp("1");
        btn();



        return view;
    }

    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_metro());
            }
        });

        xianlu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtXianlu.setText("地铁1号线");
                getOkHttp("1");
            }
        });

        xianlu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtXianlu.setText("地铁2号线");
                getOkHttp("2");
            }
        });


        xianlu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtXianlu.setText("地铁3号线");
                getOkHttp("3");
            }
        });


        xianlu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtXianlu.setText("地铁4号线");
                getOkHttp("4");
            }
        });
    }

    private void getOkHttp(String id) {
        new OKHttpTo()
                .setUrl("getSubwaysImage")
                .setJSONObject("id",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        url = jsonObject.optString("image");
                        getImage();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImage() {
        new OkHttpToImage()
                .setUrl(url)
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        xianlu1 = view.findViewById(R.id.xianlu1);
        xianlu2 = view.findViewById(R.id.xianlu2);
        xianlu3 = view.findViewById(R.id.xianlu3);
        xianlu4 = view.findViewById(R.id.xianlu4);
        txtXianlu = view.findViewById(R.id.txt_xianlu);
        img = view.findViewById(R.id.img);
    }
}

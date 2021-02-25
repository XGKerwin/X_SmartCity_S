package com.example.x_smartcity_s.Fragment.Service;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.Fragment.Fragment_Service_Outpatient;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetUserInfo;
import com.example.x_smartcity_s.bean.HospitalList;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  11:11
 */

public class Fragment_Service_Outpatient_msg extends Fragment {

    private HospitalList list;
    private ImageView titleBack;
    private TextView titleTitle;
    private ImageView image;
    private TextView txtmsg;
    private LinearLayout txt_Inquire;
    private LinearLayout btn_registered;
    private String user;
    private List<GetUserInfo> infos;

    public Fragment_Service_Outpatient_msg(HospitalList list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_outpatient_msg, container, false);
        initView(view);

        titleTitle.setText(list.getHospitalName());

        user = App.getUserida();

        getData();

        getUser();

        btn();

        return view;
    }

    private void btn() {
        txt_Inquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Outpatient_msg_inquire(list));
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Outpatient());
            }
        });

        btn_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null) {
                    Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    getFragment(new Fragment_Service_Outpatient_msg_registered(list));
                }

            }
        });

    }




    private void getUser() {
        if (infos == null) {
            infos = new ArrayList<>();
        } else {
            infos.clear();
        }
        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid", user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        infos.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUserInfo>>() {
                                }.getType()));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }


    private void getData() {

        txtmsg.setText(list.getDesc());
        new OkHttpToImage()
                .setUrl(list.getPicture())
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
        image = view.findViewById(R.id.image);
        txtmsg = view.findViewById(R.id.txtmsg);
        txt_Inquire = view.findViewById(R.id.txt_Inquire);
        btn_registered = view.findViewById(R.id.btn_registered);
    }
}

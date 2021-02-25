package com.example.x_smartcity_s.Fragment.My;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.My_Order_adapter;
import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetOrderById;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  10:08
 */

public class Fragment_My_Order extends Fragment {

    private TextView txtFanhui;
    private TextView txtLeixing;
    private RecyclerView recyclerview;
    private TextView txtTime;
    private TextView txtJine;
    private String userid;
    private List<GetOrderById> getOrderByIds;
    private My_Order_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_order, container, false);
        initView(view);
        userid = App.getUserida();
        btn();

        getOkHttp();

        return view;
    }

    private void getOkHttp() {
        if (getOrderByIds == null) {
            getOrderByIds = new ArrayList<>();
        } else {
            getOrderByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getOrderById")
                .setJSONObject("id", userid)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        txtLeixing.setText(jsonObject.optString("type"));
                        txtTime.setText(jsonObject.optString("date"));
                        txtJine.setText("金额："+jsonObject.optString("cost"));
                        getOrderByIds.addAll((Collection<? extends GetOrderById>) new Gson().fromJson(jsonObject.optString("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetOrderById>>(){}.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void show() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(linearLayoutManager);

        adapter = new My_Order_adapter(getOrderByIds);
        recyclerview.setAdapter(adapter);

    }

    private void btn() {
        txtFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_My());
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        txtFanhui = view.findViewById(R.id.txt_fanhui);
        txtLeixing = view.findViewById(R.id.txt_leixing);
        recyclerview = view.findViewById(R.id.recyclerview);
        txtTime = view.findViewById(R.id.txt_time);
        txtJine = view.findViewById(R.id.txt_jine);
    }
}

package com.example.x_smartcity_s.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_Payment_Cost_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.AccountByUserId;
import com.example.x_smartcity_s.bean.GetUserInfo;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Service_Payment_Cost extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerview;
    private String user;
    private String shuifei;
    private List<AccountByUserId> accountByUserIds,userIds;
    private Service_Payment_Cost_adapter adapter;
    private List<GetUserInfo> getUserInfos;
    private String name1;

    public Fragment_Service_Payment_Cost(String user, String s) {
        this.user = user;
        this.shuifei = s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_payment_water, container, false);
        initView(view);

        titleTitle.setText(shuifei);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_Payment());
            }
        });

        getOkHttp1();

        return view;
    }

    private void getOkHttp1() {
        if (getUserInfos == null){
            getUserInfos = new ArrayList<>();
        }else {
            getUserInfos.clear();
        }
        new OKHttpTo().setUrl("getUserInfo")
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        name1 = jsonObject1.optString("name");
                        getOkHttp();

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkHttp() {
        if (accountByUserIds == null){
            accountByUserIds = new ArrayList<>();
        }else {
            accountByUserIds.clear();
        }
        new OKHttpTo()
                .setUrl("accountByUserId")
                .setJSONObject("userid",user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        accountByUserIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<AccountByUserId>>(){}.getType()));
                        getshow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getshow() {
        if (userIds == null){
            userIds = new ArrayList<>();
        }else {
            userIds.clear();
        }
        for (int i=0;i<accountByUserIds.size();i++){
            AccountByUserId byUserId = accountByUserIds.get(i);

            if (byUserId.getType().equals(shuifei)){
                userIds.add(byUserId);
            }
        }

        getSHow1();
    }

    private void getSHow1() {
        if (adapter == null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);

            adapter = new Service_Payment_Cost_adapter(userIds,name1);
        }else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}

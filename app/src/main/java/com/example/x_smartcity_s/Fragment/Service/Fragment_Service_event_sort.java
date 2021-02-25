package com.example.x_smartcity_s.Fragment.Service;

import android.os.Bundle;
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

import com.example.x_smartcity_s.Adapter.Service_event_sort_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetActionsByType;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  16:26
 */
public class Fragment_Service_event_sort extends Fragment {
    private String id;   //活动类型   1 娱乐 2 体育 3 学习 4 政治

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerview;
    private List<GetActionsByType> byTypes;
    private Service_event_sort_adapter adapter;

    public Fragment_Service_event_sort(String s) {
        this.id = s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_event_sort, container, false);
        initView(view);

        gettitle();
        getOKHttp();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFagmenrt(new Fragment_Service_event());
            }
        });

        return view;
    }

    private void getOKHttp() {
        if (byTypes == null){
            byTypes = new ArrayList<>();
        }else {
            byTypes.clear();
        }
        new OKHttpTo()
                .setUrl("getActionsByType")
                .setJSONObject("typeid",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        byTypes.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionsByType>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);

                        adapter = new Service_event_sort_adapter(byTypes,getActivity());
                        recyclerview.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getFagmenrt(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void gettitle() {
        switch (id){
            case "1":
                titleTitle.setText("娱乐");
                break;
            case "2":
                titleTitle.setText("体育");
                break;
            case "3":
                titleTitle.setText("学习");
                break;
            case "4":
                titleTitle.setText("政治");
                break;
        }
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}

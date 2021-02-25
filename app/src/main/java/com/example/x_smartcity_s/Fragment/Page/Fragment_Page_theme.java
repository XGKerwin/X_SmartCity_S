package com.example.x_smartcity_s.Fragment.Page;

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

import com.example.x_smartcity_s.Adapter.Page_theme_adapter1;
import com.example.x_smartcity_s.Fragment.Fragment_Page;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetNEWsList;
import com.example.x_smartcity_s.bean.GetNewsInfoBySubject;
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
 * date   : 2021/2/21  16:21
 */
public class Fragment_Page_theme extends Fragment {

    private String string;
    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerview;
    private List<GetNewsInfoBySubject> infoBySubjects;
    private List<GetNEWsList> neWsLists,list;

    public Fragment_Page_theme(String s) {
        this.string = s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_page_thenme, container, false);
        initView(view);
        titleTitle.setText(string);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Page());
            }
        });
        
        getOkHttp();

        return view;
    }

    private void getOkHttp() {
        if (infoBySubjects == null){
            infoBySubjects = new ArrayList<>();
        }else {
            infoBySubjects.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsInfoBySubject")
                .setJSONObject("subject",string)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        infoBySubjects.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNewsInfoBySubject>>(){}.getType()));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getdata() {
        if (neWsLists == null){
            neWsLists = new ArrayList<>();
        }else {
            neWsLists.clear();
        }
        new OKHttpTo()
                .setUrl("getNEWsList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        neWsLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>(){}.getType()));
                        getshow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getshow() {
        if (list == null){
            list = new ArrayList<>();
        }else {
            list.clear();
        }
        for (int i=0;i<infoBySubjects.size();i++){
            GetNewsInfoBySubject info = infoBySubjects.get(i);
            for (int j=0;j<neWsLists.size();j++){
                GetNEWsList n = neWsLists.get(j);
                if (info.getNewsid().equals(n.getNewsid())){
                    list.add(n);
                    if (list.size()==infoBySubjects.size()){
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);

                        Page_theme_adapter1 adapter1 = new Page_theme_adapter1(list,getActivity());
                        recyclerview.setAdapter(adapter1);

                    }
                }
            }
        }
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

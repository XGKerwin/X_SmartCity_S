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

import com.example.x_smartcity_s.Adapter.Service_metro_Line_adapter;
import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_metro;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetAllStationById;
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
 * date   : 2021/2/22  14:59
 */
public class Fragment_Service_metro_line extends Fragment {

    private String id;
    private ImageView back;
    private TextView txtDidian1;
    private TextView txtDidian2;
    private RecyclerView recyclerView;
    private String didian;
    private List<GetAllStationById> allStationByIds;
    private Service_metro_Line_adapter adapter;


    public Fragment_Service_metro_line(String subwayid, String didian) {
        this.id = subwayid;
        this.didian = didian;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_metro_line, container, false);
        initView(view);
        getOkHttp();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_metro());
            }
        });
        return view;
    }

    private void getOkHttp() {
        if (allStationByIds == null){
            allStationByIds = new ArrayList<>();
        }else {
            allStationByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getAllStationById")
                .setJSONObject("id", id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        allStationByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetAllStationById>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);

                        adapter = new Service_metro_Line_adapter(allStationByIds,didian);
                        recyclerView.setAdapter(adapter);

                        txtDidian1.setText(allStationByIds.get(0).getStationname());
                        txtDidian2.setText(allStationByIds.get(allStationByIds.size()-1).getStationname());


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        txtDidian1 = view.findViewById(R.id.txt_didian1);
        txtDidian2 = view.findViewById(R.id.txt_didian2);
        recyclerView = view.findViewById(R.id.recyclerview);
    }
}

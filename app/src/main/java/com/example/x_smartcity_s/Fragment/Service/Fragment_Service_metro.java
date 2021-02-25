package com.example.x_smartcity_s.Fragment.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Service_metro_adapter;
import com.example.x_smartcity_s.Fragment.Fragment_Service;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetSubwaysByStation;
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
 * date   : 2021/2/22  14:42
 */
public class Fragment_Service_metro extends Fragment {

    private ImageView back;
    private EditText edDidian;
    private Button btnSousuo;
    private ImageView btnLuxian;
    private RecyclerView recyclerView;
    private String S_didian = "建国门站";
    private List<GetSubwaysByStation> subwaysByStations;
    private Service_metro_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_metro, container, false);
        initView(view);
        
        btn();
        getOkhttp();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service());
            }
        });

        btnLuxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_metro_path());
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void btn() {
    }

    private void getOkhttp() {
        if (subwaysByStations == null){
            subwaysByStations = new ArrayList<>();
        }else {
            subwaysByStations.clear();
        }
        new OKHttpTo()
                .setUrl("getSubwaysByStation")
                .setJSONObject("stationName",S_didian)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        subwaysByStations.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetSubwaysByStation>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);

                        if (subwaysByStations.size() == 0 ){
                            Toast.makeText(getContext(),"没有找到"+S_didian,Toast.LENGTH_SHORT).show();
                        }else {
                            adapter = new Service_metro_adapter(subwaysByStations,getActivity(),S_didian);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        edDidian = view.findViewById(R.id.ed_didian);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        btnLuxian = view.findViewById(R.id.btn_luxian);
        recyclerView = view.findViewById(R.id.recyclerview);
    }
}

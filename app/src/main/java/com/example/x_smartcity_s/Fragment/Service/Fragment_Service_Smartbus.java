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

import com.example.x_smartcity_s.Adapter.Service_Smartbus_adapter;
import com.example.x_smartcity_s.Fragment.Fragment_Service;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.BusList;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Service_Smartbus extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private RecyclerView recyclerview;
    private TextView titleWode;
    private List<BusList> busLists;
    private Service_Smartbus_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_smartbus, container, false);
        initView(view);
        titleTitle.setText("智慧巴士");

        getOkHttp();

        btn();

        return view;
    }

    private void btn() {
        titleWode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_smartbus_wo());
            }
        });

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service());
            }
        });
    }

    private void getOkHttp() {
        if (busLists == null){
            busLists = new ArrayList<>();
        }else {
            busLists.clear();
        }
        new OKHttpTo()
                .setUrl("busList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<BusList>>() {}.getType()));

                        if (adapter == null){
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerview.setLayoutManager(linearLayoutManager);

                            adapter = new Service_Smartbus_adapter(busLists,getActivity());
                            recyclerview.setAdapter(adapter);
                        }else {
                            adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }


    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).addToBackStack(null).commit();
    }

    private void initView(View view) {
        titleBack = (ImageView) view.findViewById(R.id.title_back);
        titleTitle = (TextView) view.findViewById(R.id.title_title);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        titleWode = (TextView) view.findViewById(R.id.title_wode);
    }
}

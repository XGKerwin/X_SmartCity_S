package com.example.x_smartcity_s.Fragment.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.News_leibie_adapter;
import com.example.x_smartcity_s.Fragment.Fragment_news;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetNEWsList;
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
 * date   : 2021/2/21  11:23
 */
public class Fragment_news_leibie extends Fragment {


    private ImageView imgBack;
    private TextView title;
    private RecyclerView recyclerView;
    private String string;
    private List<GetNEWsList> neWsLists,list;
    private News_leibie_adapter adapter;

    public Fragment_news_leibie(String s) {
        this.string = s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_news_leibie, container, false);
        initView(view);


        title.setText(string);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_news());
            }
        });

        getOkHttp();

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkHttp() {
        if (neWsLists == null){
            neWsLists = new ArrayList<>();
        }else {
            neWsLists.clear();
        }
        new OKHttpTo()
                .setUrl("getNEWsList")
                .setJSONObject("keys",string)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        neWsLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>(){}.getType()));
                        setdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void setdata() {
        if (list == null){
            list = new ArrayList<>();
        }else {
            list.clear();
        }

        for (int i=0;i<neWsLists.size();i++){
            GetNEWsList getNEWsList = neWsLists.get(i);
            if (string.equals(getNEWsList.getNewsType())){
                list.add(getNEWsList);
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new News_leibie_adapter(list,getActivity());
        recyclerView.setAdapter(adapter);



    }

    private void initView(View view) {
        imgBack = view.findViewById(R.id.img_back);
        title = view.findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.recyclerview);
    }
}

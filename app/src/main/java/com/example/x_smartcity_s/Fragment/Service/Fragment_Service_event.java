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
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.x_smartcity_s.Adapter.Service_event_adapter;
import com.example.x_smartcity_s.Fragment.Fragment_Service;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.Util.MyRecyclerView;
import com.example.x_smartcity_s.bean.GetActionImages;
import com.example.x_smartcity_s.bean.GetRecommandAction;
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
 * date   : 2021/2/22  16:13
 */
public class Fragment_Service_event extends Fragment {

    private ImageView titleBack;
    private TextView titleTitle;
    private ViewFlipper viewFlipper;
    private LinearLayout btnrecreation;
    private LinearLayout btnsports;
    private LinearLayout btnstudy;
    private LinearLayout btnpolitics;
    private MyRecyclerView recyclerview;
    private List<ImageView> imageViews;
    private List<GetActionImages> imagesList;
    private List<GetRecommandAction> actions;
    private Service_event_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_event, container, false);
        initView(view);
        titleTitle.setText("活动");
        btn();

        imageViews = new ArrayList<>();

        getImageview();

        getremen();     //热门活动

        return view;
    }

    private void btn() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service());
            }
        });
        //娱乐
        btnrecreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_event_sort("1"));
            }
        });
        //体育
        btnsports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_event_sort("2"));
            }
        });
        //学习
        btnstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_event_sort("3"));
            }
        });
        //政治
        btnpolitics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_event_sort("4"));
            }
        });

    }

    private void getremen() {
        if (actions == null){
            actions = new ArrayList<>();
        }else {
            actions.clear();
        }
        new OKHttpTo()
                .setUrl("getRecommandAction")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        actions.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetRecommandAction>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);

                        adapter = new Service_event_adapter(actions,getActivity());
                        recyclerview.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getImageview() {
        if (imagesList == null){
            imagesList = new ArrayList<>();
        }else {
            imagesList.clear();
        }
        new OKHttpTo()
                .setUrl("getActionImages")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        imagesList.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionImages>>(){}.getType()));
                        showimg();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showimg() {
        for (int i=0;i<imagesList.size();i++){
            int finalI = i;
            new OkHttpToImage().setUrl(imagesList.get(i).getImage())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            try {
                                ImageView imageView = new ImageView(getContext());
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageViews.add(imageView);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getFragment(new Fragment_Service_event_msg(imagesList.get(finalI).getId()));
                                    }
                                });
                                if (imageViews.size() == 5){
                                    for (int i = 0; i < imageViews.size(); i++) {
                                        viewFlipper.addView(imageViews.get(i));
                                    }
                                    viewFlipper.startFlipping();
                                }
                            }catch (NullPointerException e){

                            }
                        }

                        @Override
                        public void onFailure(Call call, IOException obj) {

                        }
                    }).start();
        }
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        viewFlipper = view.findViewById(R.id.view_flipper);
        btnrecreation = view.findViewById(R.id.btn_recreation);
        btnsports = view.findViewById(R.id.btn_sports);
        btnstudy = view.findViewById(R.id.btn_study);
        btnpolitics = view.findViewById(R.id.btn_politics);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}

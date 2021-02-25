package com.example.x_smartcity_s.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Page_News_adapter;
import com.example.x_smartcity_s.Adapter.Page_Service_adapter;
import com.example.x_smartcity_s.Adapter.Page_theme_adapter;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetImages;
import com.example.x_smartcity_s.bean.GetNEWsList;
import com.example.x_smartcity_s.bean.GetRecommend;
import com.example.x_smartcity_s.bean.Service_info;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/10  17:08
 */
public class Fragment_Page extends Fragment {

    private EditText edSeek;
    private LinearLayout btnSousuo;
    private ViewFlipper viewFlipper;
    private RecyclerView recycler_service;
    private RecyclerView recycler_theme;
    private LinearLayout newsLayout;
    private RecyclerView recycler_news;
    private List<ImageView> imageViews;

    private List<GetImages> getImages;
    private List<Service_info> service_infos;
    private List<GetRecommend> getRecommends;
    private List<GetNEWsList> getNEWSContents,getseek;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_page, container, false);
        initView(view);

        imageViews = new ArrayList<>();
        getImagView();      //轮播图
        getfuwu();          //关于服务的内容
        gettheme();         //热门主题
        getnews();          //新闻
        getseek();


        return view;
    }

    private void getseek() {
        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edSeek.getText().toString();
                if (s.equals("")){
                    Toast.makeText(getContext(),"搜索内容不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    getokhttpseek(s);
                }

            }
        });
    }

    private void getokhttpseek(String s) {
        if (getseek == null){
            getseek = new ArrayList<>();
        }else {
            getseek.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsByKeys")
                .setJSONObject("keys",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getseek.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>(){}.getType()));

                        if (getseek.size()==0){
                            Toast.makeText(getContext(),"没有搜到此内容",Toast.LENGTH_SHORT).show();
                        }else {
                            getFragment(new FragmentPage_seek(getseek));
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getnews() {
        if (getRecommends == null){
            getRecommends = new ArrayList<>();
        }else {
            getRecommends.clear();
        }
        new OKHttpTo()
                .setUrl("getRecommend")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getRecommends.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetRecommend>>(){}.getType()));
                        shownews();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void shownews() {
        if (getNEWSContents == null){
            getNEWSContents = new ArrayList<>();
        }else {
            getNEWSContents.clear();
        }

        for (int i=0;i<getRecommends.size();i++){
            GetRecommend recommend = getRecommends.get(i);
            getokhttpnews(recommend.getNewsid());
        }
    }

    private void getokhttpnews(int i) {
        new OKHttpTo()
                .setUrl("getNEWSContent")
                .setJSONObject("newsid", i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        GetNEWsList getNEWSContent = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL")
                                        .optJSONObject(0).toString(),
                                GetNEWsList.class);
                        getNEWSContents.add(getNEWSContent);

                        if (getNEWSContents.size() == getRecommends.size()) {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recycler_news.setLayoutManager(linearLayoutManager);

                            Page_News_adapter adapter = new Page_News_adapter(getActivity(),getNEWSContents);
                            recycler_news.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void gettheme() {
        new OKHttpTo()
                .setUrl("getAllSubject")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<String> strings = new ArrayList<>();
                        String jsonArray[] = jsonObject.optString("ROWS_DETAIL").replace("[", "").replace("]", "").split(",");
                        for (int i = 0; i < jsonArray.length; i++) {
                            strings.add(jsonArray[i].trim());
                        }

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                        recycler_theme.setLayoutManager(gridLayoutManager);

                        Page_theme_adapter theme_adapter = new Page_theme_adapter(getActivity(),strings);
                        recycler_theme.setAdapter(theme_adapter);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getfuwu() {
        if (service_infos == null) {
            service_infos = new ArrayList<>();
        } else {
            service_infos.clear();
        }

        for (int i = 1; i < 10; i++) {
            getOkHttpinfo(i);
        }

    }

    private void getOkHttpinfo(int i) {
        new OKHttpTo()
                .setUrl("service_info")
                .setJSONObject("serviceid", i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        Service_info service_info = new Gson().fromJson(jsonObject1.toString(), Service_info.class);
                        service_infos.add(service_info);
                        if (service_infos.size() == 9) {
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),5);   //2行
                            recycler_service.setLayoutManager(gridLayoutManager);

                            Page_Service_adapter adapter = new Page_Service_adapter(service_infos,getActivity());
                            recycler_service.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImagView() {
        if (getImages == null) {
            getImages = new ArrayList<>();
        } else {
            getImages.clear();
        }
        new OKHttpTo()
                .setUrl("getImages")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getImages.addAll((Collection<? extends GetImages>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetImages>>() {
                                }.getType()));
                        getImag();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImag() {
        for (int i = 0; i < getImages.size(); i++) {
            final int finalI = i + 1;
            new OkHttpToImage()
                    .setUrl(getImages.get(i).getPath())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            try {
                                ImageView imageView = new ImageView(getContext());
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageViews.add(imageView);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getFragment(new Fragment_xinwen1(finalI,1));
                                    }
                                });
                                if (imageViews.size() == 5) {
                                    for (int i = 0; i < imageViews.size(); i++) {
                                        viewFlipper.addView(imageViews.get(i));
                                    }
                                    viewFlipper.startFlipping();
                                }
                            } catch (NullPointerException e) {

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
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        viewFlipper = view.findViewById(R.id.view_flipper);
        recycler_service = view.findViewById(R.id.recycler_service);
        recycler_theme = view.findViewById(R.id.recycler_theme);
        newsLayout = view.findViewById(R.id.news_layout);
        recycler_news = view.findViewById(R.id.recycler_news);
        edSeek = view.findViewById(R.id.ed_seek);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
    }
}

package com.example.x_smartcity_s.Fragment.Party;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.Party_News_adapter;
import com.example.x_smartcity_s.OnClickItem;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetImages;
import com.example.x_smartcity_s.bean.GetNEWsList;
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
 * date   : 2021/2/10  17:09
 */

public class Fragment_Party extends Fragment {
    private ViewFlipper viewFlipper;
    private RecyclerView listview;
    private ImageView imgXuexi;
    private ImageView imgHuodong;
    private ImageView imgSuishoupai;
    private ImageView imgBaoming;
    private List<ImageView> imageViews;
    private List<GetImages> getImages;
    private List<GetNEWsList> neWsLists;
    private Party_News_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_party, container, false);
        initView(view);

        imageViews = new ArrayList<>();
        getImage();
        getNews();
        btn();

        return view;
    }

    private void btn() {

        imgXuexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Party_study());
            }
        });

        //活动
        imgHuodong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Party_event());
            }
        });
        //活动报名
        imgBaoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Party_Apply());
            }
        });

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getNews() {
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

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        listview.setLayoutManager(linearLayoutManager);
                        adapter = new Party_News_adapter(neWsLists,getContext());
                        listview.setAdapter(adapter);
                        adapter.setOnClickItem(new OnClickItem() {
                            @Override
                            public void onClick(int position, String name) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(name));
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImage() {
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
                        getImages.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetImages>>(){}.getType()));
                        getImag();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImag() {
        for (int i = 0; i < getImages.size(); i++) {
            final int finalI = i;
            try {
                final ImageView imageView = new ImageView(getContext());

            new OkHttpToImage()
                    .setUrl(getImages.get(i).getPath())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            imageView.setImageBitmap(bitmap);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageViews.add(imageView);
                            if (imageViews.size() == 5) {
                                for (int i = 0; i < imageViews.size(); i++) {
                                    viewFlipper.addView(imageViews.get(i));
                                }
                                viewFlipper.startFlipping();
                            }
                        }

                        @Override
                        public void onFailure(Call call, IOException obj) {

                        }
                    }).start();
            }catch (NullPointerException e){

            }
        }
    }

    private void initView(View view) {
        viewFlipper = view.findViewById(R.id.view_flipper);
        listview = view.findViewById(R.id.listview);
        imgXuexi = view.findViewById(R.id.img_xuexi);
        imgHuodong = view.findViewById(R.id.img_huodong);
        imgSuishoupai = view.findViewById(R.id.img_suishoupai);
        imgBaoming = view.findViewById(R.id.img_baoming);
    }
}

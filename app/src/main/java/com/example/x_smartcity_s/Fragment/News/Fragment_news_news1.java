package com.example.x_smartcity_s.Fragment.News;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Adapter.News_news1_adapter;
import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.Fragment.Fragment_Page;
import com.example.x_smartcity_s.Fragment.Fragment_news;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.Util.MyRecyclerView;
import com.example.x_smartcity_s.bean.GetCommitById;
import com.example.x_smartcity_s.bean.GetNEWsList;
import com.example.x_smartcity_s.net.OKHttpTo;
import com.example.x_smartcity_s.net.OkHttpLo;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  14:05
 */
public class Fragment_news_news1 extends Fragment {
    private GetNEWsList list;
    private ImageView back;
    private TextView ABiaoti;
    private TextView xinwenTitle;
    private ImageView xinwenImg;
    private TextView xinwenMsg;
    private TextView btnXiepinglun;
    private String user1;
    private MyRecyclerView recyclerview;
    private List<GetCommitById> commitByIds;
    private int zhuye;

    public Fragment_news_news1(GetNEWsList list) {
        this.list = list;
    }

    public Fragment_news_news1(GetNEWsList news, int i) {
        this.list = news;
        zhuye = i;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_news_news1, container, false);
        initView(view);
        user1 = App.getUserida();
        getdata();

        getpinglun();
        btn();

        return view;
    }

    private void getdata() {
        xinwenTitle.setText(list.getTitle());
        xinwenMsg.setText(list.getAbstractX());
        new OkHttpToImage()
                .setUrl(list.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        xinwenImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }
    private void getpinglun() {
        if (commitByIds == null){
            commitByIds = new ArrayList<>();
        }else {
            commitByIds.clear();;
        }

        new OKHttpTo()
                .setUrl("getCommitById")
                .setJSONObject("id",list.getNewsid())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        commitByIds.addAll((Collection<? extends GetCommitById>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetCommitById>>(){}.getType()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);


                        News_news1_adapter adapter = new News_news1_adapter(commitByIds);
                        recyclerview.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zhuye ==1){
                    getFragment(new Fragment_Page());
                }else {
                    getFragment(new Fragment_news());
                }


            }
        });

        btnXiepinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null) {
                    Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    getdia();
                }
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void getdia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_yijian, null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView fanhui = view.findViewById(R.id.dia_yijian_quxiao);
        TextView queding = view.findViewById(R.id.dia_yijian_queding);
        final EditText editText = view.findViewById(R.id.dia_yijian_edit);

        final Dialog dialog = builder.show();

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String neirong = editText.getText().toString();
                if (neirong.equals("")) {
                    Toast.makeText(getContext(), "请输入您的意见", Toast.LENGTH_SHORT).show();
                } else {
                    getpublicOpinion(neirong, dialog);
                }
            }
        });

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void getpublicOpinion(String neirong, Dialog dialog) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);

        /**
         * {"username":"user1","newsid":"1","commit":"评论","commitTime":"yyyy.MM.dd HH:mm:ss"}
         */

        new OKHttpTo()
                .setUrl("publicComit")
                .setJSONObject("username",user1)
                .setJSONObject("newsid", list.getNewsid())
                .setJSONObject("commit", neirong)
                .setJSONObject("commitTime", time)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        getpinglun();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        ABiaoti = view.findViewById(R.id.A_biaoti);
        xinwenTitle = view.findViewById(R.id.xinwen_title);
        xinwenImg = view.findViewById(R.id.xinwen_img);
        xinwenMsg = view.findViewById(R.id.xinwen_msg);
        btnXiepinglun = view.findViewById(R.id.btn_xiepinglun);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}

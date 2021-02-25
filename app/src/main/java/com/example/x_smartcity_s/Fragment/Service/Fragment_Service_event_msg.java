package com.example.x_smartcity_s.Fragment.Service;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
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

import com.example.x_smartcity_s.Adapter.Service_event_msg_adapter;
import com.example.x_smartcity_s.Adapter.Service_event_msgcomment_adapter;
import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.Fragment.Service.Fragment_Service_event;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.Util.MyRecyclerView;
import com.example.x_smartcity_s.bean.GetActionById;
import com.example.x_smartcity_s.bean.GetActionCommitById;
import com.example.x_smartcity_s.bean.GetRecommandAction;
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
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  17:02
 */
public class Fragment_Service_event_msg extends Fragment {
    private int id;
    private ImageView titleBack;
    private TextView titleTitle;
    private TextView txtTitle;
    private ImageView img;
    private TextView txtMsg;
    private TextView txtJiaru;
    private RecyclerView recyclerviewTuijian;
    private EditText edPinglun;
    private TextView txtTijiao;
    private MyRecyclerView recyclerviewPinglun;
    private List<GetActionById> actionByIds;
    private List<GetRecommandAction> actions;
    private List<GetActionCommitById> commitByIds;
    private Service_event_msg_adapter msg_adapter;
    private Service_event_msgcomment_adapter msgcomment_adapter;
    private String user;

    public Fragment_Service_event_msg(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service_event_msg, container, false);
        initView(view);
        titleTitle.setText("活动详情");
        user = App.getUserida();
        
        getOkHttp();

        getOkHttptuijian(); //推荐活动

        getActionCommitById();      //获取评论

        btn();

        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Service_event());
            }
        });
        return view;
    }

    private void btn() {
        txtJiaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"您已成功报名\""+actionByIds.get(0).getName()+"\"",Toast.LENGTH_SHORT).show();
            }
        });

        txtTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null) {
                    Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    String s = edPinglun.getText().toString();
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "评论内容不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String str = simpleDateFormat.format(date);
                        getOkHttppinglun(s, str);
                    }
                }

            }
        });

    }

    private void getOkHttppinglun(String s, String str) {
        /**
         * {"id":"2","userid":"4","commitTime":"2020-10-3 10:00:00","commitContent":"666"}
         */
        new OKHttpTo()
                .setUrl("publicActionCommit")
                .setJSONObject("id", id)
                .setJSONObject("userid", user)
                .setJSONObject("commitTime", str)
                .setJSONObject("commitContent", s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        edPinglun.setText("");
                        Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                        getActionCommitById();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getActionCommitById() {
        if (commitByIds == null) {
            commitByIds = new ArrayList<>();
        } else {
            commitByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getActionCommitById")
                .setJSONObject("id", id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        commitByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionCommitById>>() {
                                }.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerviewPinglun.setLayoutManager(linearLayoutManager);

                        if (msgcomment_adapter == null) {
                            msgcomment_adapter = new Service_event_msgcomment_adapter(commitByIds);
                            recyclerviewPinglun.setAdapter(msgcomment_adapter);
                        } else {
                            msgcomment_adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getOkHttptuijian() {
        if (actions == null) {
            actions = new ArrayList<>();
        } else {
            actions.clear();
        }
        new OKHttpTo()
                .setUrl("getRecommandAction")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        actions.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetRecommandAction>>() {
                                }.getType()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerviewTuijian.setLayoutManager(linearLayoutManager);

                        msg_adapter = new Service_event_msg_adapter(actions,getActivity());
                        recyclerviewTuijian.setAdapter(msg_adapter);


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkHttp() {
        if (actionByIds == null) {
            actionByIds = new ArrayList<>();
        } else {
            actionByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getActionById")
                .setJSONObject("id", id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        actionByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionById>>() {
                                }.getType()));
                        txtTitle.setText(actionByIds.get(0).getName());
                        txtMsg.setText(actionByIds.get(0).getDescription());
                        getImage(actionByIds.get(0).getImage());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getImage(String image) {
        new OkHttpToImage()
                .setUrl(image)
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        titleBack = view.findViewById(R.id.title_back);
        titleTitle = view.findViewById(R.id.title_title);
        txtTitle = view.findViewById(R.id.txt_title);
        img = view.findViewById(R.id.img);
        txtMsg = view.findViewById(R.id.txt_msg);
        txtJiaru = view.findViewById(R.id.txt_jiaru);
        recyclerviewTuijian = view.findViewById(R.id.recyclerview_tuijian);
        edPinglun = view.findViewById(R.id.ed_pinglun);
        txtTijiao = view.findViewById(R.id.txt_tijiao);
        recyclerviewPinglun = view.findViewById(R.id.recyclerview_pinglun);
    }
}

package com.example.x_smartcity_s.Fragment.My;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_s.Activity.UserActivity;
import com.example.x_smartcity_s.App;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.Util.MyImageView_round;
import com.example.x_smartcity_s.bean.GetUserInfo;
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
 * date   : 2021/2/10  17:12
 */

public class Fragment_My extends Fragment {

    private MyImageView_round imgHead;
    private TextView txtUser;
    private LinearLayout btnUC;
    private LinearLayout btnOrder;
    private LinearLayout btnPass;
    private LinearLayout btnFeedback;
    private LinearLayout btnExit;
    private String user1;
    private List<GetUserInfo> getUserInfoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my, container, false);
        initView(view);

        user1 = App.getUserida();

        if (user1 == null){
            imgHead.setImageResource(R.drawable.dang18);
        }else {
            getOkHttp();
        }

        btn();

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        user1 = App.getUserida();

        if (user1 == null){

        }else {
            getOkHttp();
        }
    }

    private void getOkHttp() {
        user1 = App.getUserida();

        if (user1 == null){
            return;
        }
        if (getUserInfoList == null){
            getUserInfoList = new ArrayList<>();
        }else {
            getUserInfoList.clear();
        }

        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid",user1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getUserInfoList.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUserInfo>>(){}.getType()));
                        txtUser.setText(getUserInfoList.get(0).getName());
                        getOkHttpImage();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkHttpImage() {
        new OkHttpToImage()
                .setUrl(getUserInfoList.get(0).getAvatar())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        imgHead.setImageBitmap(bitmap);
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

    private void btn() {
        txtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserActivity.class);
                startActivityForResult(intent,1);
            }
        });
        //个人中心
        btnUC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_My_UC(getUserInfoList));
                }
            }
        });
        //订单列表
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFragment(new Fragment_My_Order());
                }
            }
        });
        //密码修改
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getDialog();
                }
            }
        });
        //意见反馈
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    getFeedback();
                }
            }
        });
        //退出
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 == null){
                    Toast.makeText(getContext(),"请登录",Toast.LENGTH_SHORT).show();
                }else {
                    App.setUserida(null);
                    Toast.makeText(getContext(),"已退出",Toast.LENGTH_SHORT).show();
                    getOkHttp();
                    txtUser.setText("请登录");
                    imgHead.setImageResource(R.drawable.dang18);
                }
            }
        });

    }

    private void getFeedback() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_yijian,null);
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
                if (neirong.equals("")){
                    Toast.makeText(getContext(),"请输入您的意见",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
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

    private void getDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_mima,null);
        builder.setView(view);
        builder.setCancelable(false);
        final EditText edpass1 = view.findViewById(R.id.ed_pass1);
        final EditText edpass2 = view.findViewById(R.id.ed_pass2);
        TextView queding = view.findViewById(R.id.txt_queding);
        TextView quxiao = view.findViewById(R.id.txt_quxiao);
        final Dialog dialog = builder.show();

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = edpass1.getText().toString();
                String pass2 = edpass2.getText().toString();
                if (pass1.equals("")){
                    Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                }else if (pass2.equals("")){
                    Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                }else if (pass1.equals(pass2)){
                    setPwd(pass1);
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(),"两次密码输入的不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void setPwd(String pass1) {
        /**
         * {userid:"1",password:"123"}
         */
        new OKHttpTo()
                .setUrl("setPwd")
                .setJSONObject("userid",user1)
                .setJSONObject("password",pass1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(IOException obj) {
                        Toast.makeText(getContext(),"修改失败",Toast.LENGTH_SHORT).show();
                    }
                }).start();
    }

    private void initView(View view) {
        imgHead = view.findViewById(R.id.img_head);
        txtUser = view.findViewById(R.id.txt_user);
        btnUC = view.findViewById(R.id.btn_UC);
        btnOrder = view.findViewById(R.id.btn_order);
        btnPass = view.findViewById(R.id.btn_pass);
        btnFeedback = view.findViewById(R.id.btn_feedback);
        btnExit = view.findViewById(R.id.btn_exit);
    }
}

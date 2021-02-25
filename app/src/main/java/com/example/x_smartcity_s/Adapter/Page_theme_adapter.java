package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Fragment.Page.Fragment_Page_theme;
import com.example.x_smartcity_s.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  15:35
 */
public class Page_theme_adapter extends RecyclerView.Adapter<Page_theme_adapter.ViewHolder> {

    private FragmentActivity activity;
    private List<String> strings;


    public Page_theme_adapter(FragmentActivity activity, List<String> strings) {
        this.strings = strings;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_page_theme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s = strings.get(position);
        switch (s) {
            case "电影":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_dianying);
                break;
            case "国庆专题":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_guoqing);
                break;
            case "抗肺炎":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_kangfeiyan);
                break;
            case "烈士纪念日":
                holder.zhuyeZhutiImg.setImageResource(R.drawable.zhuti_lieshi);
                break;
        }

        holder.zhuyeZhutiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Page_theme(s));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView zhuyeZhutiImg;

        public ViewHolder(@NonNull View view) {
            super(view);
            zhuyeZhutiImg = view.findViewById(R.id.zhuye_zhuti_img);
        }
    }
}

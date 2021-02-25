package com.example.x_smartcity_s.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.Fragment.News.Fragment_news_news1;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.Util.MyImageView_arc;
import com.example.x_smartcity_s.bean.GetNEWsList;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  13:31
 */
public class News_leibie_adapter extends RecyclerView.Adapter<News_leibie_adapter.ViewHolder> {

    private List<GetNEWsList> lists;
    private FragmentActivity activity;


    public News_leibie_adapter(List<GetNEWsList> list, FragmentActivity activity) {
        this.lists = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_leibie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNEWsList neWsList = lists.get(position);
        holder.xinwenTitle.setText(neWsList.getTitle());
        holder.xinwenMsg.setText(neWsList.getAbstractX());

        new OkHttpToImage()
                .setUrl(neWsList.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.xinwenImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

        holder.xinwenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_news_news1(neWsList));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        if (lists.size() == 0) return 0;
        return lists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout xinwenLayout;
        private MyImageView_arc xinwenImg;
        private TextView xinwenTitle;
        private TextView xinwenMsg;

        public ViewHolder(@NonNull View view) {
            super(view);
            xinwenLayout = view.findViewById(R.id.xinwen_layout);
            xinwenImg = view.findViewById(R.id.xinwen_img);
            xinwenTitle = view.findViewById(R.id.xinwen_title);
            xinwenMsg = view.findViewById(R.id.xinwen_msg);
        }
    }
}

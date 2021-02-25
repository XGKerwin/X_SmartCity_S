package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  14:00
 */
public class Party_study_adapter extends RecyclerView.Adapter<Party_study_adapter.ViewHolder> {

    private int img[];
    private String[] title;
    private String[] msg;

    public Party_study_adapter(int[] imagepython, String[] s_pyhton, String[] s_pythonmsg) {
        this.img = imagepython;
        this.title = s_pyhton;
        this.msg = s_pythonmsg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_party_study, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.xinwenImg.setImageResource(img[position]);
        holder.xinwenTitle.setText(title[position]);
        holder.xinwenMsg.setText(msg[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout xinwenLayout;
        private ImageView xinwenImg;
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

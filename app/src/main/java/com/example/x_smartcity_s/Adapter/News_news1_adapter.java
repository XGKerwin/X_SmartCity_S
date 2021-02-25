package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetCommitById;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  14:21
 */
public class News_news1_adapter extends RecyclerView.Adapter<News_news1_adapter.ViewHolder> {

    private List<GetCommitById> commitByIds;

    public News_news1_adapter(List<GetCommitById> commitByIds) {
        this.commitByIds = commitByIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetCommitById commitById = commitByIds.get(position);

        holder.xinwenTitle.setText(commitById.getReviewer());
        holder.xinwenMag.setText(commitById.getCommit());
        holder.xinwenTime.setText(commitById.getCommitTime());
    }

    @Override
    public int getItemCount() {
        return commitByIds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView xinwenTitle;
        private TextView xinwenMag;
        private TextView xinwenTime;

        public ViewHolder(@NonNull View view) {
            super(view);
            xinwenTitle = view.findViewById(R.id.xinwen_title);
            xinwenMag = view.findViewById(R.id.xinwen_mag);
            xinwenTime = view.findViewById(R.id.xinwen_time);
        }
    }
}

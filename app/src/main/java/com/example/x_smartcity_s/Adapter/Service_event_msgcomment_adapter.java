package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetActionCommitById;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  8:56
 */
public class Service_event_msgcomment_adapter extends RecyclerView.Adapter<Service_event_msgcomment_adapter.ViewHolder> {

    private List<GetActionCommitById> commitByIds;

    public Service_event_msgcomment_adapter(List<GetActionCommitById> commitByIds) {
        this.commitByIds = commitByIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_event_msg_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetActionCommitById commitById = commitByIds.get(position);
        holder.xinwenTitle.setText(commitById.getUsername());
        holder.xinwenMag.setText(commitById.getCommitContent());
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

package com.example.x_smartcity_s.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetAllStationById;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  15:08
 */
public class Service_metro_Line_adapter extends RecyclerView.Adapter<Service_metro_Line_adapter.ViewHolder> {

    private List<GetAllStationById> allStationByIds;
    private String didian;

    public Service_metro_Line_adapter(List<GetAllStationById> allStationByIds, String didian) {
        this.allStationByIds = allStationByIds;
        this.didian = didian;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetAllStationById allStationById = allStationByIds.get(position);
        holder.txtDidian.setText(allStationById.getStationname());
        holder.txtTime.setText(allStationById.getStationIndex());
        holder.txtJvli.setText(allStationById.getDistance());
        if (didian.equals(allStationById.getStationname())){
            holder.txtDidian.setTextColor(Color.RED);
            holder.txtTime.setTextColor(Color.RED);
            holder.txtJvli.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return allStationByIds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDidian;
        private TextView txtTime;
        private TextView txtJvli;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtDidian = view.findViewById(R.id.txt_didian);
            txtTime = view.findViewById(R.id.txt_time);
            txtJvli = view.findViewById(R.id.txt_jvli);
        }
    }
}

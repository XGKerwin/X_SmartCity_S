package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetParkingHistory;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  15:27
 */
public class Service_Parking_list_adapter extends RecyclerView.Adapter<Service_Parking_list_adapter.ViewHolder> {

    private List<GetParkingHistory> getParkingHistories;

    public Service_Parking_list_adapter(List<GetParkingHistory> getParkingHistories) {
        this.getParkingHistories = getParkingHistories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_parking_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetParkingHistory getParkingHistory = getParkingHistories.get(position);
        holder.cp.setText(getParkingHistory.getCarNum());
        holder.feiyong.setText("收费："+getParkingHistory.getCharge()+"元");
        holder.time1.setText("入："+getParkingHistory.getInTime());
        holder.time2.setText("出："+getParkingHistory.getOutTime());
        switch (getParkingHistory.getParkingid()){
            case "1":
                holder.tcc.setText("德百停车场");
                break;
            case "2":
                holder.tcc.setText("天衢停车场");
                break;
            case "3":
                holder.tcc.setText("奥德乐停车场");
                break;
            case "4":
                holder.tcc.setText("银座大学路店停车场");
                break;
            case "5":
                holder.tcc.setText("中心广场停车场");
                break;
            case "6":
                holder.tcc.setText("人民医院停车场");
                break;
            case "7":
                holder.tcc.setText("大剧院停车场");
                break;
            case "8":
                holder.tcc.setText("长河公园停车场");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return getParkingHistories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cp;
        private TextView tcc;
        private TextView feiyong;
        private TextView time1;
        private TextView time2;

        public ViewHolder(@NonNull View view) {
            super(view);
            cp = view.findViewById(R.id.cp);
            tcc = view.findViewById(R.id.tcc);
            feiyong = view.findViewById(R.id.feiyong);
            time1 = view.findViewById(R.id.time1);
            time2 = view.findViewById(R.id.time2);
        }
    }
}

package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.AccountByUserId;

import java.util.List;

public class Service_Payment_Cost_adapter extends RecyclerView.Adapter<Service_Payment_Cost_adapter.ViewHolder> {

    private List<AccountByUserId> accountByUserIds;
    private String name1;

    public Service_Payment_Cost_adapter(List<AccountByUserId> userIds, String name1) {
        this.accountByUserIds = userIds;
        this.name1 = name1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_payment_cost, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AccountByUserId userId = accountByUserIds.get(position);
        holder.txtTitle.setText(userId.getType());
        holder.txtHuming.setText(name1);
        holder.bianhao.setText("编号："+userId.getAccountId());
        holder.danwei.setText("单位："+userId.getUnit());
        holder.txtZhuzhi.setText("住址："+userId.getAccountAddress());
        holder.txtKeyong.setText("可用余额："+userId.getBanlance());
        int s = userId.getBanlance() - userId.getCost();
        holder.txtQianfei.setText("欠费余额："+s);
    }

    @Override
    public int getItemCount() {
        return accountByUserIds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtHuming;
        private TextView bianhao;
        private TextView danwei;
        private TextView txtZhuzhi;
        private TextView txtKeyong;
        private TextView txtQianfei;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtHuming = itemView.findViewById(R.id.txt_huming);
            bianhao = itemView.findViewById(R.id.bianhao);
            danwei = itemView.findViewById(R.id.danwei);
            txtZhuzhi = itemView.findViewById(R.id.txt_zhuzhi);
            txtKeyong = itemView.findViewById(R.id.txt_keyong);
            txtQianfei = itemView.findViewById(R.id.txt_qianfei);
        }
    }
}

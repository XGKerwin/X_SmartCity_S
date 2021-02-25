package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetOrderById;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  10:14
 */
public class My_Order_adapter extends RecyclerView.Adapter<My_Order_adapter.ViewHolder> {

    private List<GetOrderById> getOrderByIds;

    public My_Order_adapter(List<GetOrderById> getOrderByIds) {
        this.getOrderByIds = getOrderByIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetOrderById orderById = getOrderByIds.get(position);
        holder.txtDidian.setText(orderById.getBusiness());
        holder.txtName.setText(orderById.getCommodity());
        holder.txtJiage.setText("价格"+orderById.getPrice());
        holder.txtShuliang.setText("数量"+orderById.getCount());
    }

    @Override
    public int getItemCount() {
        if (getOrderByIds == null) return 0;
        return getOrderByIds.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDidian;
        private TextView txtName;
        private TextView txtShuliang;
        private TextView txtJiage;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtDidian = view.findViewById(R.id.txt_didian);
            txtName = view.findViewById(R.id.txt_name);
            txtShuliang = view.findViewById(R.id.txt_shuliang);
            txtJiage = view.findViewById(R.id.txt_jiage);
        }
    }
}

package com.example.x_smartcity_s.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetNEWsList;

import java.util.List;

public class Service_Payment_News_adapter extends RecyclerView.Adapter<Service_Payment_News_adapter.ViewHolder> {

    private List<GetNEWsList> getNEWsLists;

    public Service_Payment_News_adapter(List<GetNEWsList> getNEWsLists) {
        this.getNEWsLists = getNEWsLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service_payment_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNEWsList list = getNEWsLists.get(position);
        holder.txt.setText(list.getTitle());
    }

    @Override
    public int getItemCount() {
        return getNEWsLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView txt;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            txt = view.findViewById(R.id.txt);
        }
    }
}

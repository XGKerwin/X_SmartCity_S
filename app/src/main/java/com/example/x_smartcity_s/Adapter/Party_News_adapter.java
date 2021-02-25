package com.example.x_smartcity_s.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.OnClickItem;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetNEWsList;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/22  9:33
 */
public class Party_News_adapter extends RecyclerView.Adapter<Party_News_adapter.ViewHolder> {

    private List<GetNEWsList> getNEWsLists;
    private Context context;
    public OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public Party_News_adapter(List<GetNEWsList> neWsLists, Context context) {
        this.getNEWsLists = neWsLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_party_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNEWsList neWsList = getNEWsLists.get(position);
        holder.dangjianTitle.setText(neWsList.getTitle());
        holder.dangjianLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position,neWsList.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return getNEWsLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout dangjianLayout;
        private TextView dangjianTitle;

        public ViewHolder(@NonNull View view) {
            super(view);
            dangjianLayout = view.findViewById(R.id.dangjian_layout);
            dangjianTitle = view.findViewById(R.id.dangjian_title);
        }
    }
}

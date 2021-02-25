package com.example.x_smartcity_s.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.OnClickItem;
import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.GetServiceByType;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  16:48
 */
public class Service_adapter extends RecyclerView.Adapter<Service_adapter.ViewHolder> {

    private List<GetServiceByType> getServiceByTypes;

    public Service_adapter(List<GetServiceByType> typeList) {
        this.getServiceByTypes = typeList;
    }

    public OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetServiceByType byType = getServiceByTypes.get(position);
        holder.fuwuTxt.setText(byType.getServiceName());

        new OkHttpToImage()
                .setUrl(byType.getIcon())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.fuwuImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position,holder.fuwuTxt.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return getServiceByTypes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView fuwuImg;
        private TextView fuwuTxt;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.btn_registered);
            fuwuImg = view.findViewById(R.id.fuwu_img);
            fuwuTxt = view.findViewById(R.id.fuwu_txt);
        }
    }
}

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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_s.R;
import com.example.x_smartcity_s.bean.Service_info;
import com.example.x_smartcity_s.net.OkHttpLoImage;
import com.example.x_smartcity_s.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/21  15:21
 */
public class Page_Service_adapter extends RecyclerView.Adapter<Page_Service_adapter.ViewHolder> {

    private List<Service_info> service_infos;
    private FragmentActivity activity;

    public Page_Service_adapter(List<Service_info> service_infos, FragmentActivity activity) {
        this.service_infos = service_infos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_page_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 9){
            holder.adapterZhuyeWodeImg.setImageResource(R.drawable.fuwu_gengduo);
            holder.adapterZhuyeWodeTxt.setText("更多服务");
        }else {
            Service_info service_info = service_infos.get(position);
            holder.adapterZhuyeWodeTxt.setText(service_info.getServiceName());
            new OkHttpToImage()
                    .setUrl(service_info.getIcon())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            holder.adapterZhuyeWodeImg.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onFailure(Call call, IOException obj) {

                        }
                    }).start();
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout adapterZhuyeWodeLayout;
        private ImageView adapterZhuyeWodeImg;
        private TextView adapterZhuyeWodeTxt;

        public ViewHolder(@NonNull View view) {
            super(view);
            adapterZhuyeWodeLayout = view.findViewById(R.id.adapter_zhuye_wode_layout);
            adapterZhuyeWodeImg = view.findViewById(R.id.adapter_zhuye_wode_img);
            adapterZhuyeWodeTxt = view.findViewById(R.id.adapter_zhuye_wode_txt);
        }
    }
}

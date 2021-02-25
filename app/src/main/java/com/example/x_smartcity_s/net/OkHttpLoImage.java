package com.example.x_smartcity_s.net;

import android.graphics.Bitmap;
import android.telecom.Call;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/11  16:26
 */
public interface OkHttpLoImage {
    void onResponse(Call call, Bitmap bitmap);

    void onFailure(Call call, IOException obj);
}

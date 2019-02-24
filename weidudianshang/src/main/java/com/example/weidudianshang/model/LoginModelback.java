package com.example.weidudianshang.model;

import com.example.weidudianshang.util.RetrofitCallback;

import java.util.Map;

public interface LoginModelback {
    void getData(String mPath, Map<String,String> map,int type, String userId, String sessionId, RetrofitCallback retrofitCallback);
}

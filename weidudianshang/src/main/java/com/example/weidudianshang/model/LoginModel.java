package com.example.weidudianshang.model;

import com.example.weidudianshang.entity.BannerBean;
import com.example.weidudianshang.entity.DataBean;
import com.example.weidudianshang.entity.LoginBean;
import com.example.weidudianshang.entity.RegBean;
import com.example.weidudianshang.util.RetrofitCallback;
import com.example.weidudianshang.util.RetrofitUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginModel implements LoginModelback{

    @Override
    public void getData(String mPath, Map<String, String> map, int type, String userId, String sessionId, RetrofitCallback retrofitCallback) {
        switch (type){
            case 1:
                getlogin(mPath,map,retrofitCallback);
                break;
            case 2:
                getregister(mPath,map,retrofitCallback);
                break;
            case 3:
                getshuju(mPath,map,retrofitCallback);
                break;
            case 4:
                getbanner(mPath,map,retrofitCallback);
                break;
        }
    }

    private void getbanner(String mPath, Map<String, String> map, final RetrofitCallback retrofitCallback) {
        RetrofitUtil.getInstance().doGet(mPath,null,null,null).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String result) {
                BannerBean bannerBean = new Gson().fromJson(result, BannerBean.class);
                retrofitCallback.success(bannerBean);
            }

            @Override
            public void onError(String error) {
                retrofitCallback.defeated(error);
            }
        });
    }

    //数据
    private void getshuju(String mPath, Map<String, String> map, final RetrofitCallback retrofitCallback) {
        RetrofitUtil.getInstance().doGet(mPath,null,null,null).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String result) {
                DataBean dataBean = new Gson().fromJson(result, DataBean.class);
                retrofitCallback.success(dataBean);
            }

            @Override
            public void onError(String error) {
                retrofitCallback.defeated(error);
            }
        });
    }

    //注册
    private void getregister(String mPath, Map<String, String> map,final RetrofitCallback retrofitCallback) {
        RetrofitUtil.getInstance().doPost(mPath, (HashMap<String, String>) map,null,null).setHttpListener(new RetrofitUtil.HttpListener() {
            @Override
            public void onSuccess(String result) {
                RegBean regBean = new Gson().fromJson(result, RegBean.class);
                retrofitCallback.success(regBean);
            }

            @Override
            public void onError(String error) {
                retrofitCallback.defeated(error);
            }
        });
    }
    //登录
    private void getlogin(String mPath, Map<String, String> map, final RetrofitCallback retrofitCallback) {
            RetrofitUtil.getInstance().doPost(mPath, (HashMap<String, String>) map,null,null).setHttpListener(new RetrofitUtil.HttpListener() {
                @Override
                public void onSuccess(String result) {
                    LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                    retrofitCallback.success(loginBean);
                }

                @Override
                public void onError(String error) {
                    retrofitCallback.defeated(error);
                }
            });
    }
}

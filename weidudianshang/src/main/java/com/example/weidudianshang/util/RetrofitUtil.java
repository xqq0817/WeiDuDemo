package com.example.weidudianshang.util;

import com.example.weidudianshang.network.API;
import com.example.weidudianshang.network.MyApiService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtil {

    private final MyApiService myApiService;

    private RetrofitUtil() {
        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.BASE_URL)
                .client(okHttpClient)
                .build();
        myApiService = retrofit.create(MyApiService.class);
    }

    public static RetrofitUtil getInstance() {
        return RetrofitHolder.UTIL;
    }
    private static class RetrofitHolder{
        private static final RetrofitUtil UTIL= new RetrofitUtil();
    }
    public RetrofitUtil doPost(String url, HashMap<String,String> map,String userId,String sessionId){
        myApiService.doPost(url,map,userId,sessionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtil.getInstance();
    }
    public RetrofitUtil doGet(String url, Map<String,String> map,String userId,String sessionId){
        myApiService.doGet(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return RetrofitUtil.getInstance();
    }

    private Observer observer=new Observer<ResponseBody>() {
        @Override
        public void onCompleted() {

        }
        @Override
        public void onError(Throwable e) {
            if (httpListener!=null){
                httpListener.onError(e.getMessage());
            }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            if (httpListener!=null){
                try {
                    httpListener.onSuccess(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public interface HttpListener{
        void onSuccess(String result);
        void onError(String error);
    }
    private HttpListener httpListener;
    public void setHttpListener(HttpListener listener){
       this.httpListener=listener;
    }
}

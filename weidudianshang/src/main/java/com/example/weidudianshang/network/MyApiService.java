package com.example.weidudianshang.network;

import java.util.Map;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;


public interface MyApiService {
    @POST
    Observable<ResponseBody> doPost(@Url String url ,
                                    @QueryMap Map<String ,String> map,
                                    @Header("userId") String userId,
                                    @Header("sessionId")String sessionId);
    @GET
    Observable<ResponseBody> doGet(@Url String url);

}

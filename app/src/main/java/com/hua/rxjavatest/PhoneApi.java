package com.hua.rxjavatest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/20.
 * 单例模式的API
 */

public class PhoneApi {
    /**
     * HOST地址
     */
    public static final String BASE_URL = "http://apis.baidu.com";
    /**
     * 开发者KEY
     */
    public static final String API_KEY = "0f2dbbc74c88007d71cca043035d5eba";

    private  PhoneService service;

    /**
     * 获取PhoneApi 实例
     * @return
     */
   public static PhoneApi getApi(){
       return ApiHolder.phoneApi;
   }

    private static class ApiHolder{
        private static PhoneApi phoneApi = new PhoneApi();
    }

    private PhoneApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PhoneService.class);
    }

    /**
     * 获取PhoneService实例
     */
    public PhoneService getService(){
        return service;
    }
}

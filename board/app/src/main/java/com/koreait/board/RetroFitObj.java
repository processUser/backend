package com.koreait.board;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitObj { // abstract 를 붙이면 객체화를 막는다.
    public static Retrofit rfo;
    private RetroFitObj() {} // 생성자 - 객체화를 막는다.

    public static Retrofit getInstance() {
        if(rfo == null) {
            rfo = new Retrofit.Builder().baseUrl("http://192.168.2.40:8090/").addConverterFactory(GsonConverterFactory.create()).build();
//            rfo = new Retrofit.Builder().baseUrl("http://58.237.255.35:8090/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return rfo;
    }
}

package com.example.kazi.flowersdemo.mvp;

import com.example.kazi.flowersdemo.constant.Constants;
import com.example.kazi.flowersdemo.model.FlowerModel;
import com.example.kazi.flowersdemo.service.RequestInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class IFlowerInteractor_impl implements IFlowerInteractor {

    Retrofit retrofit;
    RequestInterface requestInterface;

    //Interactor takes the role of the Connection Service
    public IFlowerInteractor_impl(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        //Initialize the retrofit plug
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)//Pass the Base URL here for making requests
                .addConverterFactory(GsonConverterFactory.create())//Create and Pass the Converter Factory for interpreting the Request Data
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Create and Pass the Call Adapter Factory for
                .client(okHttpClient)
                .build();

        //Plugin the retrofit using our Interface
        requestInterface = retrofit.create(RequestInterface.class);
    }

    @Override
    public Observable<List<FlowerModel>> getFlowerList() {
        return requestInterface.getFlowers();
    }
}

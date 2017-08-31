package com.example.kazi.flowersdemo.service;

import com.example.kazi.flowersdemo.constant.Constants;
import com.example.kazi.flowersdemo.model.FlowerModel;

import java.util.List;

//Make sure Observable is like below, not java.util!
import io.reactivex.Observable;

import retrofit2.http.GET;

public interface RequestInterface {
    @GET(Constants.FLOWERS_URI)
    Observable<List<FlowerModel>> getFlowers();
}

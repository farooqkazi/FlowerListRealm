package com.example.kazi.flowersdemo.mvp;

import com.example.kazi.flowersdemo.model.FlowerModel;

import java.util.List;

import io.reactivex.Observable;


public interface IFlowerInteractor {

    Observable<List<FlowerModel>> getFlowerList();
}

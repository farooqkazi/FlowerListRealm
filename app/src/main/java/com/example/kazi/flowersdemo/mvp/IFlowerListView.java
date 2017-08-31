package com.example.kazi.flowersdemo.mvp;

import com.example.kazi.flowersdemo.model.FlowerModel;

import java.util.List;

/** Flower List Implementation of MVP View */
public interface IFlowerListView extends MVPView {

    /** When Successful, return flowers */
    void onFetchDataSuccess(List<FlowerModel> flowerModels);

    /** When Unsuccessful, return the Error */
    void onFetchDataError(Throwable throwable);

    //void onFetchDataCompleted();
    //void onFetchDataInProgress();
}

package com.example.kazi.flowersdemo.mvp;

import com.example.kazi.flowersdemo.helper.RealmHelper;
import com.example.kazi.flowersdemo.model.FlowerModel;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by Kazi on 14/07/2017.
 */

public class IFlowerListPresenter_Impl implements IFlowerListPresenter{

    private IFlowerInteractor flowerInteractor;
    private IFlowerListView flowerListView;

    public IFlowerListPresenter_Impl(IFlowerInteractor interactor){
        this.flowerInteractor = interactor;


    }

    @Override
    public void performFlowerList() {

        //Get the Realm Instance
        RealmHelper realmHelper = new RealmHelper(Realm.getDefaultInstance());

        //Check the Network State and React to it
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {

                    //Auto Completed for you! :D
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            flowerInteractor.getFlowerList()
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.newThread())
                                    .subscribe(this::onSuccess, this::onError);
                        } else {
                            //Load Pre-Loaded Realm Cache
                            flowerListView.onFetchDataSuccess(realmHelper.getFlowers());
                        }
                    }

                    private void onError(Throwable throwable) {

                    }

                    private void onSuccess(List<FlowerModel> flowerModels) {

                        //Refresh Realm Cache
                        flowerModels.forEach(realmHelper::saveFlower);

                        //Load Refreshed Realm Cache
                        flowerListView.onFetchDataSuccess(realmHelper.getFlowers());
                    }
                });
    }

    @Override
    public void attachView(IFlowerListView mvpView) {
        this.flowerListView = mvpView;
    }

    @Override
    public void detachView() {

    }
}

package com.example.kazi.flowersdemo.helper;

import com.example.kazi.flowersdemo.model.FlowerModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveFlower(final FlowerModel flowerModel){

        //Realm Transaction to Save/Update data
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(flowerModel);
            }
        });
    }

    public List<FlowerModel> getFlowers(){
        List<FlowerModel> flowers = new ArrayList<>();

        if (realm != null && !realm.isEmpty()) {

            RealmResults<FlowerModel> flowerResults = realm.where(FlowerModel.class).findAll();
            for (FlowerModel flowerModel : flowerResults) {
                flowers.add(flowerModel);
            }
        }

        return flowers;
    }
}
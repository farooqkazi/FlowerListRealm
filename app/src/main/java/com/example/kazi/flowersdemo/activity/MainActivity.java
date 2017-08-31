package com.example.kazi.flowersdemo.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.kazi.flowersdemo.R;
import com.example.kazi.flowersdemo.adapter.FlowerListAdapter;
import com.example.kazi.flowersdemo.model.FlowerModel;
import com.example.kazi.flowersdemo.mvp.IFlowerInteractor;
import com.example.kazi.flowersdemo.mvp.IFlowerInteractor_impl;
import com.example.kazi.flowersdemo.mvp.IFlowerListPresenter;
import com.example.kazi.flowersdemo.mvp.IFlowerListPresenter_Impl;
import com.example.kazi.flowersdemo.mvp.IFlowerListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IFlowerListView{

    SwipeRefreshLayout swipeRefreshLayout;

    IFlowerListPresenter flowerListPresenter;
    IFlowerInteractor flowerInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flowerInteractor = new IFlowerInteractor_impl();
        flowerListPresenter = new IFlowerListPresenter_Impl(flowerInteractor);
        flowerListPresenter.attachView(this);

        //Register Swipe RefreshLayout in Layout
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                flowerListPresenter.performFlowerList();
            }
        });

        flowerListPresenter.performFlowerList();
    }

    @Override
    public void onFetchDataSuccess(List<FlowerModel> flowerModels) {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        if (flowerModels != null && flowerModels.size() > 0) {
            recyclerView.setAdapter(new FlowerListAdapter(flowerModels, getApplicationContext()));
        } else {
            recyclerView.setAdapter(new FlowerListAdapter(new ArrayList<FlowerModel>(0), getApplicationContext()));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchDataError(Throwable throwable) {

        Log.e("Whoops!", throwable.getLocalizedMessage());
        swipeRefreshLayout.setRefreshing(false);
    }
}
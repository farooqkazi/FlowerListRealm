package com.example.kazi.flowersdemo.mvp;

//Used for all MVP Presenters, Each View has a single Presenter
interface MVPPresenter<V extends MVPView> {

    void attachView(V mvpView);
    void detachView();
}

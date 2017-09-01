package com.example.coco.teademo.ui.contrac;

/**
 * Created by coco on 2017/8/29.
 */

public class MainPresenterImpl implements MainContract.MainPresenter {
    public MainContract.MainView view;

    public MainPresenterImpl(MainContract.MainView view) {
        this.view = view;
    }

    @Override
    public void setLocation() {

    }
}

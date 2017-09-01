package com.example.coco.teademo.ui.contrac;

/**
 * Created by coco on 2017/8/28.
 */

public class MinePresenterImpl implements MineContarct.MinePresenter {
    private MineContarct.MineView view;

    public MinePresenterImpl(MineContarct.MineView view) {
        this.view = view;
    }

    @Override
    public void hide() {
        view.hideActionBar();
    }
}

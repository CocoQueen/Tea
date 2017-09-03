package com.example.coco.teademo.ui.contrac;

/**
 * Created by coco on 2017/8/28.
 *
 * 实现类
 */

public class MinePresenterImpl implements MineContarct.MinePresenter {
    private MineContarct.MineView view;

    public MinePresenterImpl(MineContarct.MineView view) {
        this.view = view;
    }

    @Override
    public void hide() {
        //当前界面隐藏actionbar
        view.hideActionBar();
    }
}

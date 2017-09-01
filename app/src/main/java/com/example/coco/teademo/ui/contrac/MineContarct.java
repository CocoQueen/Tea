package com.example.coco.teademo.ui.contrac;

import com.example.coco.teademo.base.BasePresenter;
import com.example.coco.teademo.bean.UserInfoBean;

/**
 * Created by coco on 2017/8/28.
 */

public interface MineContarct {
     interface MineView{
        void hideActionBar();
        void showLoginView(UserInfoBean bean);
        void showLoginError();

    }
    interface MinePresenter extends BasePresenter{
        void hide();
    }
}

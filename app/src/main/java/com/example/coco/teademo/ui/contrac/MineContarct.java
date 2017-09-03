package com.example.coco.teademo.ui.contrac;

import com.example.coco.teademo.base.BasePresenter;
import com.example.coco.teademo.bean.UserInfoBean;

/**
 * Created by coco on 2017/8/28.
 */

public interface MineContarct {
     interface MineView{
         //隐藏actionbar
        void hideActionBar();
         //显示登陆成功的数据
        void showLoginView(UserInfoBean bean);
         //登陆失败
        void showLoginError();

    }
    interface MinePresenter extends BasePresenter{
        //隐藏的方法
        void hide();
    }
}

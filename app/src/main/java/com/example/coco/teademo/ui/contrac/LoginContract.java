package com.example.coco.teademo.ui.contrac;

/**
 * Created by coco on 2017/8/25.
 */

public interface LoginContract {
    interface LoginView{
        void showErrorToast(String msg);
        void showSendSuccessToast();
        void showLoginSuccessToast();
        void showPhonenumErrorToast();
        void showPorYNullToast();
        void startIntent();



    }
    interface LoginPresenter{
        void initSMS();
        void sendYzm(String phone);
        void Login(String phone,String yzm);
        void unRegister();


    }

}

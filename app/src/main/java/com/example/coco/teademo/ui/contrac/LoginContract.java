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
        //开始跳转
        void startIntent();



    }
    interface LoginPresenter{
        //初始化SMS
        void initSMS();
        //发送验证码
        void sendYzm(String phone);
        //登陆的方法
        void Login(String phone,String yzm);
        //反注册
        void unRegister();


    }

}

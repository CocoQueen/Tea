package com.example.coco.teademo.ui.contrac;

import android.os.SystemClock;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by coco on 2017/8/25.
 */

public class LoginPresenterimpl implements LoginContract.LoginPresenter {
    private LoginContract.LoginView view;
    private EventHandler eventHandler;

    public LoginPresenterimpl(LoginContract.LoginView view) {
        this.view = view;
    }

    @Override
    public void initSMS() {
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    view.showErrorToast(msg);
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        view.showSendSuccessToast();
                    }
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    view.showLoginSuccessToast();
                    }
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void sendYzm(String phone) {
        String regExp = "^(1[3,4,5,7,8][0-9]\\d{8})$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher m = pattern.matcher(phone);
        if (!TextUtils.isEmpty(phone) && m.matches()) {
            SMSSDK.getVerificationCode("86", phone);
        } else {
          view.showPhonenumErrorToast();
        }
    }

    @Override
    public void Login(String phone,String yzm) {
        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(yzm)){
           view.showPorYNullToast();
        }
        if (!TextUtils.isEmpty(yzm)) {
            SMSSDK.submitVerificationCode("86", phone, yzm);
            SystemClock.sleep(2000);
           view.startIntent();
        }
    }

    @Override
    public void unRegister() {
        //取消注册
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}

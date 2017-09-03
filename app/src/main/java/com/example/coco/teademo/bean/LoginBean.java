package com.example.coco.teademo.bean;

import android.os.Parcel;

/**
 * Created by coco on 2017/8/28.
 * 登陆界面的bean
 *
 */

public class LoginBean {
    private UserInfoBean user;

    public UserInfoBean getUser() {
        return user;
    }

    public void setUser(UserInfoBean user) {
        this.user = user;
    }



    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        this.user = in.readParcelable(UserInfoBean.class.getClassLoader());
    }


}
package com.example.coco.teademo.utils;

import android.util.Log;

import com.example.coco.teademo.api.API;
import com.example.coco.teademo.bean.BaseBean;
import com.example.coco.teademo.bean.HomeShopBean;
import com.example.coco.teademo.bean.LoginBean;
import com.example.coco.teademo.constant.BSConstant;
import com.example.coco.teademo.constant.Constant;
import com.example.coco.teademo.ui.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by coco on 2017/8/28.
 */

public class HttpHelper {
    private static HttpHelper helper = new HttpHelper();
    private Retrofit retrofit;
    private final API api;


    private HttpHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.ROOT + Constant.URL_VERSION)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }

    public static HttpHelper getInstance() {
        return helper;
    }

    public void getShopList(String openId, String location) {
        Observable<HomeShopBean> jinxunashangpu = api.homeShop("jingxuanshanghu", openId, 0, location, 1);
        jinxunashangpu.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HomeShopBean>() {
                    @Override
                    public void call(HomeShopBean homeShopBean) {
                        Log.e(TAG, "call: " + homeShopBean.getCode());
                        EventBus.getDefault().post(new MainActivity.MyEvent(homeShopBean));
                    }
                });
    }

    public void getVerifyCode(String phone, long time, String ip, String mac) {
        Observable<BaseBean> verifycode = api.getVerifycode(BSConstant.VERIFY_CODE, phone, ip, mac, time, AppUtil.encryptSign((time - 444) + "", "", phone, ip), "1");
        verifycode.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean baseBean) {
                        Log.e(TAG, "call: " + baseBean.getCode());
                    }
                });
    }

    public void login(String phone, String code) {
        Observable<BaseBean<LoginBean>> baseBean = api.login(BSConstant.LOGIN, phone, code, null, null, null, null, null);
        baseBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean<LoginBean>>() {
                    @Override
                    public void call(BaseBean<LoginBean> baseBean) {
                        if (baseBean.getCode() == 200) {
                            LoginBean obj = baseBean.getObj();
//                           UserInfoBean user = obj.getUser();
//                           user.getAvatar();//头像
//                           user.getNickname();//昵称
//                           user.getPhone();//电话号码
//                           user.getSex();//拿到性别
//                           user.getXiadanshu();//订单数量
//                           user.getShoucangshanghu();//收藏店铺
//                           user.getYouhuiquan();//优惠券
//                           user.getHuiyuanka();//会员卡
                            EventBus.getDefault().post(obj);
                        } else {
                            EventBus.getDefault().post("ERROR");

                        }

                    }
                });
    }

}

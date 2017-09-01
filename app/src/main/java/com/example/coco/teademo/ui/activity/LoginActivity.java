package com.example.coco.teademo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coco.teademo.R;
import com.example.coco.teademo.ui.contrac.LoginContract;
import com.example.coco.teademo.ui.contrac.LoginPresenterimpl;

/**
 * Created by coco on 2017/8/24.
 */

public class LoginActivity extends Activity implements View.OnClickListener, LoginContract.LoginView {

    private Button mBtn_getYzm;
    private Button mBtn_login;
    private EditText mEd_yzm;
    private EditText mEd_phone;
    private String phone;
    private LoginContract.LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        presenter=new LoginPresenterimpl(this);
        presenter.initSMS();
        initView();
    }

    private void initView() {
        mBtn_getYzm = (Button) findViewById(R.id.mBtn_getYzm);
        mBtn_login = (Button) findViewById(R.id.mBtn_login);
        mEd_yzm = (EditText) findViewById(R.id.mEd_yzm);
        mEd_phone = (EditText) findViewById(R.id.mEd_phone);
        mBtn_login.setOnClickListener(this);
        mBtn_getYzm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtn_getYzm:
                phone = mEd_phone.getText().toString().trim();
                presenter.sendYzm(phone);
                break;
            case R.id.mBtn_login:
                String yzm = mEd_yzm.getText().toString().trim();
                presenter.Login(phone,yzm);
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    @Override
    public void showErrorToast(String msg) {
        showToast(msg);

    }


    @Override
    public void showSendSuccessToast() {
        showToast("验证码发送成功。。。");
    }

    @Override
    public void showLoginSuccessToast() {
        showToast("登陆中，页面即将跳转....");
    }

    @Override
    public void showPhonenumErrorToast() {
        showToast("手机号码格式不正确");
    }

    @Override
    public void showPorYNullToast() {
        showToast("手机号码或者验证码不能为空");

    }

    @Override
    public void startIntent() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

    private void showToast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unRegister();
    }
}

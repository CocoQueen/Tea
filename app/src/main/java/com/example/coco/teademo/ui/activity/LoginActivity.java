package com.example.coco.teademo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coco.teademo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by coco on 2017/8/24.
 */

public class LoginActivity extends Activity implements View.OnClickListener {

    private Button mBtn_getYzm;
    private Button mBtn_login;
    private EditText mEd_yzm;
    private EditText mEd_phone;
    private EventHandler eventHandler;
    private String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initView();
        initSMS();

    }

    private void initSMS() {

        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "登陆成功，页面即将跳转", Toast.LENGTH_SHORT).show();


                            }
                        });
                    }
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
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
                String regExp = "^(1[3,4,5,7,8][0-9]\\d{8})$";
                Pattern pattern = Pattern.compile(regExp);
                Matcher m = pattern.matcher(phone);
                if (!TextUtils.isEmpty(phone) && m.matches()) {
                    SMSSDK.getVerificationCode("86", phone);
                } else {
                    Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mBtn_login:
                String yzm = mEd_yzm.getText().toString().trim();
                if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(yzm)){
                    Toast.makeText(this, "手机号码或者验证码不能为空", Toast.LENGTH_SHORT).show();
                }
                if (!TextUtils.isEmpty(yzm)) {
                    SMSSDK.submitVerificationCode("86", phone, yzm);
                    SystemClock.sleep(2000);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}

package com.example.coco.teademo.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coco.teademo.R;
import com.example.coco.teademo.bean.HomeShopBean;
import com.example.coco.teademo.model.MyLocationManager;
import com.example.coco.teademo.model.StatusBarManager;
import com.example.coco.teademo.ui.contrac.MainContract;
import com.example.coco.teademo.ui.contrac.MainPresenterImpl;
import com.example.coco.teademo.ui.fragment.MineFragment;
import com.example.coco.teademo.utils.HttpHelper;
import com.example.coco.teademo.utils.MeasureUtils;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends FragmentActivity implements MainContract.MainView {
    private static final String TAG = "MainActivity";

    private TextView tv_location;
    private EditText search;
    private FrameLayout fl_msg;

    private MainPresenterImpl presenter;
    private int statusBarHeight;
    private TencentLocationManager locationManager;
    private TencentLocationRequest request;
    private MineFragment mineFragment;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setPresenter(this);
        presenter.view.setActionBar(this);
        presenter.view.setStatusBar(this);
        HttpHelper.getInstance().getShopList("104cca5fad614b53e494e5198f4cdb47", "116.125584,40.232219");
        EventBus.getDefault().register(this);
        test();
    }


    private void test() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.mRg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if ((checkedId == R.id.mRb_mine)) {
                    switch2MineFragment();
                }


            }
        });
    }

    private void switch2MineFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            ft.add(R.id.fl_content, mineFragment, "mine");

        }else{
            ft.show(mineFragment);
        }
        ft.commit();
    }

    private void setPresenter(MainContract.MainView view) {
        presenter = new MainPresenterImpl(view);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showToast22(MyEvent event) {
        Toast.makeText(this, event.bean.getCode() + "hahaha", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != 0 && grantResults[1] != 0 && grantResults[2] != 0) {
            Toast.makeText(this, "请打开定位权限", Toast.LENGTH_SHORT).show();
        } else if (grantResults[0] == 0 && grantResults[1] == 0 && grantResults[2] == 0) {
            int error = locationManager.requestLocationUpdates(request, new MyLocationManager.LocationListener());
        }
    }

    @Override
    public void setActionBar(Activity act) {
        actionBar = getActionBar();
        //让actionbar使用自定义的布局样式
       actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.head);
        View view = LayoutInflater.from(this).inflate(R.layout.head, null, false);
        tv_location = (TextView) view.findViewById(R.id.tv_location);
        search = (EditText) view.findViewById(R.id.search);
        fl_msg = (FrameLayout) view.findViewById(R.id.fl_msg);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setStatusBar(Activity act) {
        StatusBarManager.setTranStatusBar(act);
        statusBarHeight = MeasureUtils.getStatusBarHeight(this);
    }

    @Override
    public void setLocationText(String address) {
        if (address == null && "".equals(address)) {
            tv_location.setText("定位中...");
        } else {
            tv_location.setText(address);
        }
    }

    public static class MyEvent {
        HomeShopBean bean;

        public MyEvent(HomeShopBean bean) {
            this.bean = bean;
        }

        public void setBean(HomeShopBean bean) {
            this.bean = bean;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}

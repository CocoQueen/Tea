package com.example.coco.teademo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by coco on 2017/8/28.
 * Fragment所继承的基类
 *    包含了初始化数据和初始化ui界面的方法
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initDate();
    }

    protected abstract void initDate();
    protected abstract View initView(LayoutInflater inflater);
}

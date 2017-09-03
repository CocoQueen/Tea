package com.example.coco.teademo.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.example.coco.teademo.R;
import com.example.coco.teademo.base.BaseFragment;

/**
 * Created by coco on 2017/9/1.
 */

public class HomeFragment extends BaseFragment {

    private View view;

    @Override
    protected void initDate() {

    }

    @Override
    protected View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.home_fragment, null, false);
        return null;
    }
}

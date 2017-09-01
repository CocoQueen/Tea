package com.example.coco.teademo.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coco.teademo.R;

/**
 * Created by coco on 2017/8/23.
 */

public class MyView extends FrameLayout {
    private TextView name, num;
    private String title;
    private int icon;
    private ImageView iv_title;


    public MyView(@NonNull Context context) {
        super(context);
        initView();
    }


    public MyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String attributeName = attrs.getAttributeName(i);
            if (attributeName.equals("text")) {
                title = attrs.getAttributeValue(i);

            }
            if (attributeName.equals("image")) {
                icon = attrs.getAttributeResourceValue(i, R.mipmap.ic_launcher_round);
            }
        }
        setNameAndIcon(title, icon);

    }

    private void setNameAndIcon(String title, int icon) {
        if (!TextUtils.isEmpty(title)) {
            name.setText(title);
        }
        if (icon != 0) {
            iv_title.setBackgroundResource(icon);
        }
    }

    public MyView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.mine_item, null, false);
        name = (TextView) view.findViewById(R.id.tv_name);
        name.setTextColor(getResources().getColor(R.color.gray));
        name.setTextSize(getResources().getDimensionPixelSize(R.dimen.mine_title_size));
        num = (TextView) view.findViewById(R.id.tv_order_num);
        iv_title = (ImageView) view.findViewById(R.id.iv_title);
        this.addView(view);

    }

    public void setNum(String num2) {
        if (!TextUtils.isEmpty(num2)) {
            num.setText(num2);
        }
    }
}

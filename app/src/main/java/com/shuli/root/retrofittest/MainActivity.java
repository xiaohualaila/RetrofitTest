package com.shuli.root.retrofittest;

import android.os.Bundle;
import android.view.View;

import com.shuli.root.retrofittest.base.BaseActivity;
import com.shuli.root.retrofittest.base.ViewHolder;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {

    }
}

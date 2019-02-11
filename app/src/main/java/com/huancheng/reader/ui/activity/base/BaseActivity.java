package com.huancheng.reader.ui.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.huancheng.reader.common.MainApplication;


/**
 * Created by leeandy007 on 2017/3/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Activity _context;
    protected MainApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MainApplication) this.getApplication();
        application.addActivity(this);

        setCustomLayout(savedInstanceState);
        initView();
        BindComponentEvent();
        initData();


    }
    /**
     * 初始化布局
     * */
    protected abstract void setCustomLayout(Bundle savedInstanceState);

    /**
     * 初始化控件
     * */
    protected abstract void initView();

    /**
     * 初始化数据
     * */
    protected abstract void initData();

    /**
     * 绑定控件事件
     * */
    protected abstract void BindComponentEvent();




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            doActivityResult(requestCode, intent);
        }
    }


    /**
     * 带返回值跳转的数据的处理方法
     * */
    protected abstract void doActivityResult(int requestCode, Intent intent);

}

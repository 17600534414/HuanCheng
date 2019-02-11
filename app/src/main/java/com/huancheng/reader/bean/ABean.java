package com.huancheng.reader.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by admin on 2018/12/18.
 */

public class ABean implements MultiItemEntity {
    //其他的正常
//    private String name;

    private int i;


    //这是返回给你的类型，默认给个类型
    @Override
    public int getItemType() {
        return i;
    }
}

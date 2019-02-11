package com.huancheng.reader.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by admin on 2018/12/19.
 */

public class ManBean implements MultiItemEntity {
    private int img;//图片
    private String typename;//类型名称
    private String entry;//条数量
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ManBean(int img, String typename, String entry) {
        this.img = img;
        this.typename = typename;
        this.entry = entry;


    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public int getItemType() {
        return code;
    }
}

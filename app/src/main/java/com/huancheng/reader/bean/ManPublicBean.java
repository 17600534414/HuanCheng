package com.huancheng.reader.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/12/20.
 */

public class ManPublicBean implements Serializable {

    private int img;//书封面
    private String bookname;//书名
    private String bookintroduce;//书介绍
    private int myimg;//个人头像
    private String myname;//个人昵称
    private String booktype;
    private int img_icon;

    public ManPublicBean(int img, String bookname, String bookintroduce, int myimg, String myname) {
        this.img = img;
        this.bookname = bookname;
        this.bookintroduce = bookintroduce;
        this.myimg = myimg;
        this.myname = myname;
    }

    public ManPublicBean(int img, String bookname, String bookintroduce, int myimg, String myname, String booktype) {
        this.img = img;
        this.bookname = bookname;
        this.bookintroduce = bookintroduce;
        this.myimg = myimg;
        this.myname = myname;
        this.booktype = booktype;
    }



    public ManPublicBean(int img, String bookname, String bookintroduce, int myimg, String myname, String booktype, int img_icon) {

        this.img = img;
        this.bookname = bookname;
        this.bookintroduce = bookintroduce;
        this.myimg = myimg;
        this.myname = myname;
        this.booktype = booktype;
        this.img_icon = img_icon;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookintroduce() {
        return bookintroduce;
    }

    public void setBookintroduce(String bookintroduce) {
        this.bookintroduce = bookintroduce;
    }

    public int getMyimg() {
        return myimg;
    }

    public void setMyimg(int myimg) {
        this.myimg = myimg;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public int getImg_icon() {
        return img_icon;
    }

    public void setImg_icon(int img_icon) {
        this.img_icon = img_icon;
    }
}

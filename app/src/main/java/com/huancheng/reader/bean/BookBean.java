package com.huancheng.reader.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/12/18.
 */

public class BookBean implements Serializable {

    private int img;
    private String bookname;
    private String booktype;
    private String type;
    private String image;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BookBean(int img, String bookname, String booktype, String type){
        this.img = img;
        this.bookname = bookname;
        this.booktype = booktype;
        this.type = type;
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

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }
}

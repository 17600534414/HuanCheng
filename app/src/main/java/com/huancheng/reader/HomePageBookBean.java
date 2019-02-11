package com.huancheng.reader;

import com.huancheng.reader.bean.TypePublicBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/1/10.
 */

public class HomePageBookBean implements Serializable {

    private String code;
    private List<List<TypePublicBean>> book;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<List<TypePublicBean>> getBook() {
        return book;
    }

    public void setBook(List<List<TypePublicBean>> book) {
        this.book = book;
    }
}

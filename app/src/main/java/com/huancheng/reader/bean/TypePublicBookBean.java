package com.huancheng.reader.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/1/9.
 */

public class TypePublicBookBean implements Serializable {
    private String code;
    private List<TypePublicBean> book;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TypePublicBean> getBook() {
        return book;
    }

    public void setBook(List<TypePublicBean> book) {
        this.book = book;
    }
}

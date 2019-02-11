package com.huancheng.reader.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/12/20.
 */

public class BoxBean implements Serializable {

    private String code;
    private String msg;
    private String states;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}

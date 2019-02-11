package com.huancheng.reader.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/12/6.
 */

public class LoginPhoneBean implements Serializable {

    private int code;
    private String msg;
    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}

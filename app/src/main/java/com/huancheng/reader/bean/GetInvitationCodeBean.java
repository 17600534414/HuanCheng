package com.huancheng.reader.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/12/12.
 */

public class GetInvitationCodeBean implements Serializable {

    private String code;
    private String msg;

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
}

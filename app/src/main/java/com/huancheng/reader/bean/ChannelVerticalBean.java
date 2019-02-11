package com.huancheng.reader.bean;

import java.util.List;

/**
 * Created by admin on 2019/1/15.
 */

public class ChannelVerticalBean extends ChannelBean {

    public List<TypePublicBean> getManchannelVertical() {
        return manchannelVertical;
    }

    public void setManchannelVertical(List<TypePublicBean> manchannelVertical) {
        this.manchannelVertical = manchannelVertical;
    }

    private List<TypePublicBean> manchannelVertical;


}

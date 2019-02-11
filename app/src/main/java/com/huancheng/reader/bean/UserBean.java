package com.huancheng.reader.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/12/12.
 */

public class UserBean implements Serializable {

//    14:11:29
//    望眼欲穿。 2018/12/13 14:11:29
//    /**宝箱状态（0.显示领取10分钟的宝箱1.显示60分钟的2.是显示180分钟的3.已经全部领取完毕）**/
//    private String states;
//    /**今日观看视频广告每日限定2次（0次.1次.2次）如果是2的话不让他增加**/
//    private String video;
//    /**今日分享次数每日限定2次（0次.1次.2次）如果是2的话不让他增加**/
//    private String share;
//
//    望眼欲穿。 2018/12/13 14:11:56
//    这是user表里新增加的字段名
//14:19:29
//    望眼欲穿。 2018/12/13 14:19:29
//
//            15:12:00
//    望眼欲穿。 2018/12/13 15:12:00
//
//    /**用户id**/
//    private String id;
//    /**用户手机号**/
//    private String tel;
//    /**用户名**/
//    private String name;
//    /**密码**/
//    private String pwd;
//    /**性别**/
//    private String sex;
//    /**年龄**/
//    private String age;
//    /**邀请码**/
//    private String invitecode;
//    /**用户头像链接**/
//    private String iconurl;
//    /**是否为vip(0:不是vip1:为vip)**/
//    private String vip;
//    /**今日书券数量**/
//    private String booksTokenday;
//    /**书券数量**/
//    private String booksToken;
//    /**最后登录时间**/
//    private Date lastLoginTime;
//    /**0-离线，1-登录**/
//    private String loginState;
//    /**QQ号**/
//    private String qq;
//    /**qq昵称**/
//    private String qqname;
//    /**微信昵称**/
//    private String wechatName;
//    /**微信openId**/
//    private String wechatOpenid;
//    /**支付宝昵称**/
//    private String alipayName;
//    /**支付宝唯一标识**/
//    private String alipayId;
//    /**签到天数 0-7 每周重置**/
//    private String signDays;
//    /**是否已经接受邀请**/
//    private String isInvited;
//    /**签到总数**/
//    private String totalSign;
//    /**今日是否签到**/
//    private String isSignToday;
//    /**注册时间**/
//    private Date registerTime;
//    /**注册渠道名称**/
//    private String registerChannelName;
//    /**注册渠道id**/
//    private String registerChannel;
//    /**状态  0：禁用   1：正常**/
//    private String state;
//    /**地址**/
//    private String address;
//    /**邀请总人数**/
//    private String inviteNum;
//    /**宝箱状态（0.显示领取10分钟的宝箱1.显示60分钟的2.是显示180分钟的3.已经全部领取完毕）**/
//    private String states;
//    /**今日观看视频广告每日限定2次（0次.1次.2次）如果是2的话不让他增加**/
//    private String video;
//    /**今日分享次数每日限定2次（0次.1次.2次）如果是2的话不让他增加**/
//    private String share;

    private String age;//生日
    private String alipayId;
    private String alipayName;
    private String books;
    private String iconurl;
    private String id;
    private String invitecode;
    private String isInvited;
    private String isSignToday;
    private String lastLoginTime;
    private String loginState;
    private String name;
    private String pwd;
    private String qq;
    private String qqname;
    private String registerChannel;
    private String registerChannelName;
    private String registerTime;
    private String sex;
    private String signDays;
    private String state;
    private String tel;
    private String totalSign;
    private String vip;
    private String wechatName;
    private String wechatOpenid;
    private String states;//宝箱状态（0.显示领取10分钟的宝箱1.显示60分钟的2.是显示180分钟的3.已经全部领取完毕）
    private String video;//今日观看视频广告每日限定2次（0次.1次.2次）如果是2的话不让他增加
    private String share;//今日分享次数每日限定2次（0次.1次.2次）如果是2的话不让他增加
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public String getAlipayName() {
        return alipayName;
    }

    public void setAlipayName(String alipayName) {
        this.alipayName = alipayName;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public String getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(String isInvited) {
        this.isInvited = isInvited;
    }

    public String getIsSignToday() {
        return isSignToday;
    }

    public void setIsSignToday(String isSignToday) {
        this.isSignToday = isSignToday;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginState() {
        return loginState;
    }

    public void setLoginState(String loginState) {
        this.loginState = loginState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQqname() {
        return qqname;
    }

    public void setQqname(String qqname) {
        this.qqname = qqname;
    }

    public String getRegisterChannel() {
        return registerChannel;
    }

    public void setRegisterChannel(String registerChannel) {
        this.registerChannel = registerChannel;
    }

    public String getRegisterChannelName() {
        return registerChannelName;
    }

    public void setRegisterChannelName(String registerChannelName) {
        this.registerChannelName = registerChannelName;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignDays() {
        return signDays;
    }

    public void setSignDays(String signDays) {
        this.signDays = signDays;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTotalSign() {
        return totalSign;
    }

    public void setTotalSign(String totalSign) {
        this.totalSign = totalSign;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }
}

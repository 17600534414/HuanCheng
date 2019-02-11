package com.huancheng.reader.util;

/**
 * Created by admin on 2018/12/5.
 */

public class HttpUtil {



    //测试
    public static final String HOST = "http://www.hzhuanqu.com:8086";
//    public static final String SOCKET_HOST = "http://www.hzhuanqu.com:8086";
    public static final String SOCKET_HOST = "http://192.168.1.7:8083";
//public static final String SOCKET_HOST = "http://47.99.94.190:8083";
    //手机登录
    public static final String LOGIN = "/novel/user/telLogin";
    //注册
    public static final String REGISTER = "/novel/user/telRegister";
    //短信验证
    public static final String CODE = "/novel/sms/getMessage";
    //忘记密码
    public static final String FORGET_PASSWORD = "/novel/user/forgetPwd";
    //邀请码
    public static final String INVITATION_CODE = "/novel/user/checkInviteCode";
    //意见与反馈
    public static final String OPINION = "/novel/feedback/addsave";
    //用户资料修改
    public static final String USER_INFO = "/novel/user/uploadFile";
    //领取宝箱
    public static final String ECEIVE_TREASURE_CHEST = "/novel/user/boxCoins";
    //签到
    public static final String SIGN_IN = "/novel/user/boxShareign";
    //签到展示
    public static final String SIGN_INS = "/novel/user/boxShareigns";
    //获取男频类别的书籍
    public static final String BOOK_TYPE = "/novel/bookstack/list";
    //获取男频类别的书籍
    public static final String BOOK_MAN_TYPE = "/novel/bookstack/getBookList";
    //获取女频类别的书籍
    public static final String BOOK_WOMAN_TYPE = "/novel/bookstack/getBookLists";
    //获取完结类别的书籍
    public static final String BOOK_END_ALL = "/novel/bookstack/complete";
    //主页的各类数据
    public static final String BOOK_TYPE_HOMEPAGE = "/novel/bookstack/pageList";

}

package com.huancheng.reader.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


//import com.iflytek.cloud.SpeechConstant;
//import com.iflytek.cloud.SpeechUtility;
//import com.mzth.study.bean.app_info.AppBean;
//import com.mzth.study.bean.app_info.MemberPrivilegesBean;
//import com.umeng.socialize.PlatformConfig;
//import com.umeng.socialize.UMShareAPI;

import com.huancheng.reader.bean.UserBean;

import java.util.ArrayList;
import java.util.Map;


public class MainApplication extends Application {

    private static ArrayList<Activity> list = new ArrayList<Activity>();

    private static Context context;
    //app基本配置
//    private static AppBean appBean;
    //会员特权基本配置
//    private static MemberPrivilegesBean privilegesBean;
//
//    public static MemberPrivilegesBean getPrivilegesBean() {
//        return privilegesBean;
//    }
//
//    public static void setPrivilegesBean(MemberPrivilegesBean privilegesBean) {
//        MainApplication.privilegesBean = privilegesBean;
//    }
//
//    public static AppBean getAppBean() {
//        return appBean;
//    }
//
//    public static void setAppBean(AppBean appBean) {
//        MainApplication.appBean = appBean;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        UMShareAPI.get(this);
        //各个平台的配置分享
//        PlatformConfig.setWeixin("wx0b08e5914c5dad95","9abc240a5402162d72d2500b3d8c7a33");
//        PlatformConfig.setWeixin("wxe2ddeb2a7e807caa","54cc2ab3c32e6a28832a6963db6361e3");
//        PlatformConfig.setQQZone("1106566051","KNKdCQz9qyt5lpxl");
////        PlatformConfig.setQQZone("1106133915","oUhNZAB6GJo3jvi0");
//        //讯飞语音合成
//        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a3de9a1");

    }
    /**
     * 添加Activity到集合中
     */
    public void addActivity(Activity activity) {
        list.add(activity);
    }

    public Context getContext() {
        return context;
    }
    // 用于存放倒计时时间
    public static Map<String, Long> map;
    /**
     * 从集合中移除Activity
     */
    public void removeActivity(Activity activity) {
        list.remove(activity);
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(base);
//    }

    /**
     * 关闭所有的Activity
     */
    public static void closeActivity() {
        for (Activity activity : list) {
            if (null != activity) {
                activity.finish();
            }
        }
    }

}

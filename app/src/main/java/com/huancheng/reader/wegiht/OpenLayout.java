package com.huancheng.reader.wegiht;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

/**
 * Created by admin on 2018/12/17.
 */

public class OpenLayout {

    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout drawerLayout;
    //左边菜单开关事件
    public void openLeftLayout(View view) {
        if (drawerLayout.isDrawerOpen(view)) {
            drawerLayout.closeDrawer(view);
        } else {
            drawerLayout.openDrawer(view);
        }
    }

    // 右边菜单开关事件
//    public void openRightLayout(View view) {
//        if (drawerLayout.isDrawerOpen(main_right_drawer_layout)) {
//            drawerLayout.closeDrawer(main_right_drawer_layout);
//        } else {
//            drawerLayout.openDrawer(main_right_drawer_layout);
//        }
//    }
}

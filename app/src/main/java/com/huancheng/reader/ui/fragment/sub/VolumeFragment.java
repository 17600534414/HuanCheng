package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.UserBean;
import com.huancheng.reader.common.SaveLocalData;
import com.huancheng.reader.ui.activity.sub.CollectionActivity;
import com.huancheng.reader.ui.activity.sub.ModifyDataActivity;
import com.huancheng.reader.ui.activity.sub.NewsActivity;
import com.huancheng.reader.ui.activity.sub.RechargeActivity;
import com.huancheng.reader.ui.activity.sub.SetUpActivity;
import com.huancheng.reader.ui.activity.sub.SignActivity;
import com.huancheng.reader.ui.activity.sub.VipActivity;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.util.DateUtile;
import com.huancheng.reader.util.SharedPreferencesUtil;
import com.huancheng.reader.util.StringUtil;
import com.huancheng.reader.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/12/3.
 */

@SuppressLint("ValidFragment")
public class VolumeFragment extends BaseFragment {

    @BindView(R.id.btn_activity_task)
    RadioButton btnActivityTask;
    @BindView(R.id.btn_activity_activity)
    RadioButton btnActivityActivity;
    @BindView(R.id.ll_activity_content)
    LinearLayout llActivityContent;

    private FragmentTransaction ft;
    private ActivityActivityFragment activityActivityFragment;
    private ActivityTaskFragment activityTaskFragment;
    @SuppressLint("ValidFragment")
    public VolumeFragment(Context context, int resId) {
        super(context, resId);
        _context = context;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        ButterKnife.bind(this,v);
        initDefultView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void BindComponentEvent() {

    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
    @OnClick({R.id.btn_activity_task, R.id.btn_activity_activity})
    public void onViewClicked(View view) {
        FragmentManager fm = getChildFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment
        switch (view.getId()) {
            case R.id.btn_activity_task://任务
                if (activityTaskFragment == null) {
                    activityTaskFragment = new ActivityTaskFragment(_context, R.layout.fragment_activity_task);
                    ft.add(R.id.ll_activity_content, activityTaskFragment);
                } else {
                    ft.show(activityTaskFragment);
                }
                ft.commit();
                break;
            case R.id.btn_activity_activity://活动
                if (activityActivityFragment == null) {
                    activityActivityFragment = new ActivityActivityFragment(_context, R.layout.fragment_activity_activity);
                    ft.add(R.id.ll_activity_content, activityActivityFragment);
                } else {
                    ft.show(activityActivityFragment);
                }
                ft.commit();
                break;

        }
    }
    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (activityTaskFragment != null) {
            fragmentTransaction.hide(activityTaskFragment);
        }
        if (activityActivityFragment != null) {
            fragmentTransaction.hide(activityActivityFragment);
        }
    }

    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getChildFragmentManager();
        ft = fm.beginTransaction();
        activityTaskFragment = new ActivityTaskFragment(_context, R.layout.fragment_activity_task);
        ft.add(R.id.ll_activity_content, activityTaskFragment);
        ft.commit();
        btnActivityTask.setChecked(true);
    }
}

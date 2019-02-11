package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/12/4.
 */

@SuppressLint("ValidFragment")
public class ClassifyFragment extends BaseFragment implements BaseFragment.FragmentCallBack {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.rb_man_classification)
    RadioButton rbManClassification;
    @BindView(R.id.rb_woman_classification)
    RadioButton rbWomanclassification;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.ll_content_classification)
    LinearLayout llContentClassification;

    Unbinder unbinder;



    private ManFragment manFragment;//男生
    private WomanFragment womanFragment;//女生
    private FragmentTransaction ft;

    @SuppressLint("ValidFragment")
    public ClassifyFragment(Context context, int resId) {
        super(context, resId);
        _context = context;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        ButterKnife.bind(this, v);
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

    @OnClick({R.id.iv_left_title_bar, R.id.iv_right_title_bar, R.id.rb_man_classification, R.id.rb_woman_classification})
    public void onViewClicked(View view) {
        FragmentManager fm = getChildFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment
        switch (view.getId()) {
            case R.id.iv_left_title_bar:

                break;
            case R.id.iv_right_title_bar:
                break;

            case R.id.rb_man_classification://男生
                if (manFragment == null) {
                    manFragment = new ManFragment(_context, R.layout.fragment_man);
                    ft.add(R.id.ll_content_classification, manFragment);
                } else {
                    ft.show(manFragment);
                }
                ft.commit();
                break;
            case R.id.rb_woman_classification://女生
                if (womanFragment == null) {
                    womanFragment = new WomanFragment(_context, R.layout.fragment_woman);
                    ft.add(R.id.ll_content_classification, womanFragment);
                } else {
                    ft.show(womanFragment);
                }
                ft.commit();
                break;
        }
    }

    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (manFragment != null) {
            fragmentTransaction.hide(manFragment);
        }
        if (womanFragment != null) {
            fragmentTransaction.hide(womanFragment);
        }
    }

    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getChildFragmentManager();
        ft = fm.beginTransaction();
        manFragment = new ManFragment(_context, R.layout.fragment_man);
        ft.add(R.id.ll_content_classification, manFragment);
        ft.commit();

    }

    @Override
    public void setResult(Object... param) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




}

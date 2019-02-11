package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.wegiht.AutoVerticalScrollTextView;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/12/3.
 */

@SuppressLint("ValidFragment")
public class BookReviewFragment extends BaseFragment {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.rb_find)
    RadioButton rbFind;
    @BindView(R.id.rb_information)
    RadioButton rbInformation;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.ll_content_find)
    LinearLayout llContentFind;

    Unbinder unbinder;

    private FragmentTransaction ft;

    private FindFragment findFragment;
    private InformationFragment informationFragment;

    @SuppressLint("ValidFragment")
    public BookReviewFragment(Context context, int resId) {
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

    @OnClick({R.id.iv_left_title_bar, R.id.rb_find, R.id.rb_information, R.id.iv_right_title_bar})
    public void onViewClicked(View view) {
        FragmentManager fm = getChildFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                break;
            case R.id.iv_right_title_bar:

                break;
            case R.id.rb_find:
                if (findFragment == null) {
                    findFragment = new FindFragment(_context, R.layout.fragment_find);
                    ft.add(R.id.ll_content_find, findFragment);
                } else {
                    ft.show(findFragment);
                }
                ft.commit();
                break;
            case R.id.rb_information:
                if (informationFragment == null) {
                    informationFragment = new InformationFragment(_context, R.layout.fragment_information);
                    ft.add(R.id.ll_content_find, informationFragment);
                } else {
                    ft.show(informationFragment);
                }
                ft.commit();
                break;
        }
    }

    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (findFragment != null) {
            fragmentTransaction.hide(findFragment);
        }
        if (informationFragment != null) {
            fragmentTransaction.hide(informationFragment);
        }
    }

    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getChildFragmentManager();
        ft = fm.beginTransaction();
        findFragment = new FindFragment(_context, R.layout.fragment_find);
        ft.add(R.id.ll_content_find, findFragment);

        ft.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

package com.huancheng.reader.ui.activity.sub;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.ui.fragment.sub.BookReviewFragment;
import com.huancheng.reader.ui.fragment.sub.ChoiceFragment;
import com.huancheng.reader.ui.fragment.sub.InteractionFragment;
import com.huancheng.reader.ui.fragment.sub.NoticeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends BaseBussActivity implements View.OnClickListener,BaseFragment.FragmentCallBack {
    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.btn_notice)
    Button btnNotice;
    @BindView(R.id.btn_interaction)
    Button btnInteraction;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    private FragmentTransaction ft;
    private NoticeFragment noticeFragment;
    private InteractionFragment interactionFragment;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = NewsActivity.this;
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        initDefultView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
        btnNotice.setOnClickListener(this);
        btnInteraction.setOnClickListener(this);
    }


    @OnClick({R.id.iv_left_title_bar,R.id.rv_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.rv_news:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment
        switch (v.getId()) {
            case R.id.btn_notice:
                if (noticeFragment == null) {
                    noticeFragment = new NoticeFragment(_context, R.layout.fragment_notice);
                    ft.add(R.id.content, noticeFragment);
                } else {
                    ft.show(noticeFragment);
                }
                break;
            case R.id.btn_interaction:
                if (interactionFragment == null) {
                    interactionFragment = new InteractionFragment(_context, R.layout.fragment_interaction);
                    ft.add(R.id.content, interactionFragment);
                } else {
                    ft.show(interactionFragment);
                }
                break;
        }
        ft.commit();
    }
    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (interactionFragment != null) {
            fragmentTransaction.hide(interactionFragment);
        }
        if (noticeFragment != null) {
            fragmentTransaction.hide(noticeFragment);
        }
    }
    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        noticeFragment = new NoticeFragment(_context, R.layout.fragment_notice);
        ft.add(R.id.content, noticeFragment);
        ft.commit();
    }

    @Override
    public void setResult(Object... param) {

    }
}

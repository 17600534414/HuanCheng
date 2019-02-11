package com.huancheng.reader.ui.activity.sub;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.TypePublicBean;
import com.huancheng.reader.bean.TypePublicBookBean;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.ui.fragment.sub.EndManFragment;
import com.huancheng.reader.ui.fragment.sub.EndWomanFragment;
import com.huancheng.reader.ui.fragment.sub.UrbanFragment;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class EndBookActivity extends BaseBussActivity implements BaseFragment.FragmentCallBack{
    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.tv_end_man)
    RadioButton tvEndMan;
    @BindView(R.id.tv_end_woman)
    RadioButton tvEndWoman;
    @BindView(R.id.ll_content_end)
    LinearLayout llContentEnd;

    private UrbanFragment urbanFragment,endManFragment,endWomanFragment;//现代都市
//    private EndManFragment endManFragment;
//    private EndWomanFragment endWomanFragment;
    private FragmentTransaction ft;
    private List<TypePublicBean> lists;


    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = EndBookActivity.this;
        setContentView(R.layout.activity_end_book);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("完结");
        initDefultView();


    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }


    @OnClick({R.id.iv_left_title_bar, R.id.iv_right_title_bar, R.id.tv_end_man, R.id.tv_end_woman})
    public void onViewClicked(View view) {
        FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.iv_right_title_bar:
                break;
            case R.id.tv_end_man://男生
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"男生",14);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (endManFragment == null) {
                    endManFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"男生",14);
                    ft.add(R.id.ll_content_end, endManFragment);
                } else {
                    ft.show(endManFragment);
                }
                ft.commit();
                break;
            case R.id.tv_end_woman://女生
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"女生",15);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (endWomanFragment == null) {
                    endWomanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"女生",15);
                    ft.add(R.id.ll_content_end, endWomanFragment);
                } else {
                    ft.show(endWomanFragment);
                }

                ft.commit();
                break;
        }
    }
    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (endManFragment != null) {
            fragmentTransaction.hide(endManFragment);
        }
        if (endWomanFragment != null) {
            fragmentTransaction.hide(endWomanFragment);
        }
    }
    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        endManFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"男生",14);
        ft.add(R.id.ll_content_end, endManFragment);
        ft.commit();
    }

    @Override
    public void setResult(Object... param) {

    }
}

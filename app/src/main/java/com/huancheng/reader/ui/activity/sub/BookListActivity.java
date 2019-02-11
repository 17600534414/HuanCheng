package com.huancheng.reader.ui.activity.sub;

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
import com.huancheng.reader.ui.fragment.sub.ConscienceFragment;
import com.huancheng.reader.ui.fragment.sub.GoddessHotFragment;
import com.huancheng.reader.ui.fragment.sub.MalegodHotFragment;
import com.huancheng.reader.ui.fragment.sub.PopularFragment;
import com.huancheng.reader.ui.fragment.sub.PopularityFragment;
import com.huancheng.reader.ui.fragment.sub.PotentialFragment;
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

public class BookListActivity extends BaseBussActivity implements BaseFragment.FragmentCallBack{


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.tv_popularity)
    RadioButton tvPopularity;
    @BindView(R.id.tv_conscience)
    RadioButton tvConscience;
    @BindView(R.id.tv_potential)
    RadioButton tvPotential;
    @BindView(R.id.tv_goddess_hot)
    RadioButton tvGoddessHot;
    @BindView(R.id.tv_malegod_hot)
    RadioButton tvMalegodHot;
    @BindView(R.id.tv_popular)
    RadioButton tvPopular;
    @BindView(R.id.ll_content_list)
    LinearLayout llContentList;


    private UrbanFragment urbanFragment,popularityFragment,conscienceFragment,potentialFragment,goddessHotFragment,malegodHotFragment,popularFragment;//现代都市
//    private PopularityFragment popularityFragment;
//    private ConscienceFragment conscienceFragment;
//    private PotentialFragment potentialFragment;
//    private GoddessHotFragment goddessHotFragment;
//    private MalegodHotFragment malegodHotFragment;
//    private PopularFragment popularFragment;


    private List<TypePublicBean> lists;
    private FragmentTransaction ft;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = BookListActivity.this;
        setContentView(R.layout.activity_book_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("榜单");
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

    @OnClick({R.id.iv_left_title_bar, R.id.iv_right_title_bar, R.id.tv_popularity, R.id.tv_conscience, R.id.tv_potential, R.id.tv_goddess_hot, R.id.tv_malegod_hot, R.id.tv_popular})
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
            case R.id.tv_popularity://人气新书榜
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"人气新书榜",16);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (popularityFragment == null) {
                    popularityFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"人气新书榜",16);
                    ft.add(R.id.ll_content_list, popularityFragment);
                } else {
                    ft.show(popularityFragment);
                }
                ft.commit();
                break;
            case R.id.tv_conscience://良心完结榜
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"良心完结榜",17);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (conscienceFragment == null) {
                    conscienceFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"良心完结榜",17);
                    ft.add(R.id.ll_content_list, conscienceFragment);
                } else {
                    ft.show(conscienceFragment);
                }
                ft.commit();
                break;
            case R.id.tv_potential://潜力飙升榜
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"潜力飙升榜",18);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (potentialFragment == null) {
                    potentialFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"潜力飙升榜",18);
                    ft.add(R.id.ll_content_list, potentialFragment);
                } else {
                    ft.show(potentialFragment);
                }
                ft.commit();
                break;
            case R.id.tv_goddess_hot://女神热搜榜
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"女神热搜榜",19);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (goddessHotFragment == null) {
                    goddessHotFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"女神热搜榜",19);
                    ft.add(R.id.ll_content_list, goddessHotFragment);
                } else {
                    ft.show(goddessHotFragment);
                }
                ft.commit();
                break;
            case R.id.tv_malegod_hot://男神热搜榜
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"男神热搜榜",20);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (malegodHotFragment == null) {
                    malegodHotFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"男神热搜榜",20);
                    ft.add(R.id.ll_content_list, malegodHotFragment);
                } else {
                    ft.show(malegodHotFragment);
                }

                ft.commit();
                break;
            case R.id.tv_popular://热门追更榜
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"热门追更榜",21);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (popularFragment == null) {
                    popularFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"热门追更榜",21);
                    ft.add(R.id.ll_content_list, popularFragment);
                } else {
                    ft.show(popularFragment);
                }

                ft.commit();
                break;
        }
    }
    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (popularityFragment != null) {
            fragmentTransaction.hide(popularityFragment);
        }
        if (conscienceFragment != null) {
            fragmentTransaction.hide(conscienceFragment);
        }
        if (potentialFragment != null) {
            fragmentTransaction.hide(potentialFragment);
        }
        if (goddessHotFragment != null) {
            fragmentTransaction.hide(goddessHotFragment);
        }
        if (malegodHotFragment != null) {
            fragmentTransaction.hide(malegodHotFragment);
        }
        if (popularFragment != null) {
            fragmentTransaction.hide(popularFragment);
        }
    }
    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        popularityFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"人气新书榜",16);
        ft.add(R.id.ll_content_list, popularityFragment);
        ft.commit();
    }

    @Override
    public void setResult(Object... param) {

    }

}

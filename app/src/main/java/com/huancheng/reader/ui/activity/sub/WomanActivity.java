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
import com.huancheng.reader.ui.fragment.sub.AncientRomanceFragment;
import com.huancheng.reader.ui.fragment.sub.FantasyRomanceFragment;
import com.huancheng.reader.ui.fragment.sub.GirlSuspenseFragment;
import com.huancheng.reader.ui.fragment.sub.ModernRomanceFragment;
import com.huancheng.reader.ui.fragment.sub.PureLoveFragment;
import com.huancheng.reader.ui.fragment.sub.UrbanFragment;
import com.huancheng.reader.ui.fragment.sub.YouthFragnent;
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

public class WomanActivity extends BaseBussActivity implements BaseFragment.FragmentCallBack{


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.tv_modern_romance)
    RadioButton tvModernRomance;
    @BindView(R.id.tv_Ancient_romance)
    RadioButton tvAncientRomance;
    @BindView(R.id.tv_fantasy_romance)
    RadioButton tvFantasyRomance;
    @BindView(R.id.tv_youth)
    RadioButton tvYouth;
    @BindView(R.id.tv_girl_suspense)
    RadioButton tvGirlSuspense;
    @BindView(R.id.tv_pure_love)
    RadioButton tvPureLove;
    @BindView(R.id.ll_content_woman)
    LinearLayout llContentWoman;

    private FragmentTransaction ft;

    private UrbanFragment urbanFragment,modernRomanceFragment,ancientRomanceFragment,fantasyRomanceFragment,youthFragnent,girlSuspenseFragment,pureLoveFragment;//现代都市
//    private ModernRomanceFragment modernRomanceFragment;
//    private AncientRomanceFragment ancientRomanceFragment;
//    private FantasyRomanceFragment fantasyRomanceFragment;
//    private YouthFragnent youthFragnent;
//    private GirlSuspenseFragment girlSuspenseFragment;
//    private PureLoveFragment pureLoveFragment;

    private List<TypePublicBean> lists;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = WomanActivity.this;
        setContentView(R.layout.activity_woman);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("女生频道");
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

    @OnClick({R.id.iv_left_title_bar, R.id.iv_right_title_bar, R.id.tv_modern_romance, R.id.tv_Ancient_romance, R.id.tv_fantasy_romance, R.id.tv_youth, R.id.tv_girl_suspense, R.id.tv_pure_love})
    public void onViewClicked(View view) {
        FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment
        switch (view.getId()) {
            case R.id.iv_left_title_bar://返回
                onBackPressed();
                break;
            case R.id.iv_right_title_bar://搜索
                break;
            case R.id.tv_modern_romance://现代言情
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"现代言情",8);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (modernRomanceFragment == null) {
                    modernRomanceFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"现代言情",8);
                    ft.add(R.id.ll_content_woman, modernRomanceFragment);
                } else {
                    ft.show(modernRomanceFragment);
                }
                ft.commit();
                break;
            case R.id.tv_Ancient_romance://古代言情
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"古装言情",9);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (ancientRomanceFragment == null) {
                    ancientRomanceFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"古装言情",9);
                    ft.add(R.id.ll_content_woman, ancientRomanceFragment);
                } else {
                    ft.show(ancientRomanceFragment);
                }
                ft.commit();
                break;
            case R.id.tv_fantasy_romance://幻想言情
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"幻想言情",10);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (fantasyRomanceFragment == null) {
                    fantasyRomanceFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"幻想言情",10);
                    ft.add(R.id.ll_content_woman, fantasyRomanceFragment);
                } else {
                    ft.show(fantasyRomanceFragment);
                }
                ft.commit();
                break;
            case R.id.tv_youth://浪漫青春
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"浪漫青春",11);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (youthFragnent == null) {
                    youthFragnent = new UrbanFragment(_context, R.layout.activity_manpublic,"浪漫青春",11);
                    ft.add(R.id.ll_content_woman, youthFragnent);
                } else {
                    ft.show(youthFragnent);
                }

                ft.commit();
                break;
            case R.id.tv_girl_suspense://女生悬疑
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"女生悬疑",12);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (girlSuspenseFragment == null) {
                    girlSuspenseFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"女生悬疑",12);
                    ft.add(R.id.ll_content_woman, girlSuspenseFragment);
                } else {
                    ft.show(girlSuspenseFragment);
                }

                ft.commit();
                break;
            case R.id.tv_pure_love://纯爱小说
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"纯爱小说",13);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (pureLoveFragment == null) {
                    pureLoveFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"纯爱小说",13);
                    ft.add(R.id.ll_content_woman, pureLoveFragment);
                } else {
                    ft.show(pureLoveFragment);
                }
                ft.commit();
                break;
        }
    }
    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (modernRomanceFragment != null) {
            fragmentTransaction.hide(modernRomanceFragment);
        }
        if (ancientRomanceFragment != null) {
            fragmentTransaction.hide(ancientRomanceFragment);
        }
        if (fantasyRomanceFragment != null) {
            fragmentTransaction.hide(fantasyRomanceFragment);
        }
        if (youthFragnent != null) {
            fragmentTransaction.hide(youthFragnent);
        }
        if (girlSuspenseFragment != null) {
            fragmentTransaction.hide(girlSuspenseFragment);
        }
        if (pureLoveFragment != null) {
            fragmentTransaction.hide(pureLoveFragment);
        }
    }
    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        modernRomanceFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"现代言情",8);
        ft.add(R.id.ll_content_woman, modernRomanceFragment);
        ft.commit();
    }

    @Override
    public void setResult(Object... param) {

    }
}

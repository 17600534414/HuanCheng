package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.TypePublicBean;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.ui.fragment.sub.UrbanFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManActivity extends BaseBussActivity implements BaseFragment.FragmentCallBack{


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.tv_urban)
    TextView tvUrban;
    @BindView(R.id.tv_fantasy)
    TextView tvFantasy;
    @BindView(R.id.tv_knight_errant)
    TextView tvKnightErrant;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_novel)
    TextView tvNovel;
    @BindView(R.id.tv_suspense)
    TextView tvSuspense;
    @BindView(R.id.tv_game)
    TextView tvGame;



    private UrbanFragment urbanFragment,fantasyFragment,knightErrantFragment,novelFragment,historyFragment,suspenseFragment,gameFragment;//现代都市
//    private FantasyFragment fantasyFragment;//玄幻奇幻
//    private KnightErrantFragment knightErrantFragment;//武侠仙侠
//    private HistoryFragment historyFragment;//历史军事
//    private NovelFragment novelFragment;//科幻小说
//    private SuspenseFragment suspenseFragment;//灵异悬疑
//    private GameFragment gameFragment;//游戏竞技
    private List<TypePublicBean> lists;

    private FragmentTransaction ft;
    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = ManActivity.this;
        setContentView(R.layout.activity_man);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("男生频道");
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

    @OnClick({R.id.iv_left_title_bar, R.id.iv_right_title_bar, R.id.tv_urban, R.id.tv_fantasy, R.id.tv_knight_errant, R.id.tv_history, R.id.tv_novel, R.id.tv_suspense, R.id.tv_game})
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
            case R.id.tv_urban://现代都市
//                urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"都市小说",1);
//                ft.add(R.id.ll_content_man, urbanFragment);
                if (urbanFragment == null) {
                    urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"都市小说",1);
                    ft.add(R.id.ll_content_man, urbanFragment);
                } else {
                    ft.show(urbanFragment);
                }

                ft.commit();
                break;
            case R.id.tv_fantasy://玄幻奇幻
//                fantasyFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"虚幻奇幻",2);
//                ft.add(R.id.ll_content_man, fantasyFragment);
                if (fantasyFragment == null) {
                    fantasyFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"玄幻奇幻",2);
                    ft.add(R.id.ll_content_man, fantasyFragment);
                } else {
                    ft.show(fantasyFragment);
                }
                ft.commit();

                break;
            case R.id.tv_knight_errant://武侠仙侠
//                knightErrantFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"武侠仙侠",3);
//                ft.add(R.id.ll_content_man, knightErrantFragment);
                if (knightErrantFragment == null) {
                    knightErrantFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"武侠仙侠",3);
                    ft.add(R.id.ll_content_man, knightErrantFragment);
                } else {
                    ft.show(knightErrantFragment);
                }

                ft.commit();
                break;
            case R.id.tv_history://历史军事
//                historyFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"历史军事",4);
//                ft.add(R.id.ll_content_man, historyFragment);
                if (historyFragment == null) {
                    historyFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"历史军事",4);
                    ft.add(R.id.ll_content_man, historyFragment);
                } else {
                    ft.show(historyFragment);
                }

                ft.commit();
                break;
            case R.id.tv_novel://科幻小说
//                novelFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"科幻末世",5);
//                ft.add(R.id.ll_content_man, novelFragment);
                if (novelFragment == null) {
                    novelFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"科幻末世",5);
                    ft.add(R.id.ll_content_man, novelFragment);
                } else {
                    ft.show(novelFragment);
                }
                ft.commit();
                break;
            case R.id.tv_suspense://灵异悬疑
//                suspenseFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"悬疑小说",6);
//                ft.add(R.id.ll_content_man, suspenseFragment);
                if (suspenseFragment == null) {
                    suspenseFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"悬疑小说",6);
                    ft.add(R.id.ll_content_man, suspenseFragment);
                } else {
                    ft.show(suspenseFragment);
                }

                ft.commit();
                break;
            case R.id.tv_game://游戏竞技
//                gameFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"游戏竞技",7);
//                ft.add(R.id.ll_content_man, gameFragment);
                if (gameFragment == null) {
                    gameFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"游戏竞技",7);
                    ft.add(R.id.ll_content_man, gameFragment);
                } else {
                    ft.show(gameFragment);
                }
                ft.commit();
                break;
        }
    }
    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (urbanFragment != null) {
            fragmentTransaction.hide(urbanFragment);
        }
        if (fantasyFragment != null) {
            fragmentTransaction.hide(fantasyFragment);
        }
        if (knightErrantFragment != null) {
            fragmentTransaction.hide(knightErrantFragment);
        }
        if (historyFragment != null) {
            fragmentTransaction.hide(historyFragment);
        }
        if (novelFragment != null) {
            fragmentTransaction.hide(novelFragment);
        }
        if (suspenseFragment != null) {
            fragmentTransaction.hide(suspenseFragment);
        }
        if (gameFragment != null) {
            fragmentTransaction.hide(gameFragment);
        }
    }
    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        urbanFragment = new UrbanFragment(_context, R.layout.activity_manpublic,"都市小说",1);
        ft.add(R.id.ll_content_man, urbanFragment);
        ft.commit();
    }

    @Override
    public void setResult(Object... param) {

    }
}

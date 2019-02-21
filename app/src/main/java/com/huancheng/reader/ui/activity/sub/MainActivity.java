package com.huancheng.reader.ui.activity.sub;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.UserBean;
import com.huancheng.reader.common.SaveLocalData;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.ui.fragment.sub.BookReviewFragment;
import com.huancheng.reader.ui.fragment.sub.BookShlefFragment;
import com.huancheng.reader.ui.fragment.sub.ChoiceFragment;
import com.huancheng.reader.ui.fragment.sub.ClassifyFragment;
import com.huancheng.reader.ui.fragment.sub.VolumeFragment;
import com.huancheng.reader.util.DateUtile;
import com.huancheng.reader.util.SharedPreferencesUtil;
import com.huancheng.reader.util.StringUtil;
import com.huancheng.reader.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseBussActivity implements View.OnClickListener, BaseFragment.FragmentCallBack {
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.btn_bookshlef)
    RadioButton btnBookshlef;
    @BindView(R.id.btn_choice)
    RadioButton btnChoice;
    @BindView(R.id.btn_volume)
    RadioButton btnVolume;
    @BindView(R.id.btn_classify)
    RadioButton btnClassify;
    @BindView(R.id.btn_bookreview)
    RadioButton btnBookreview;
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;
    @BindView(R.id.iv_hean)
    ImageView ivHean;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.te_share)
    TextView teShare;
    @BindView(R.id.tv_volume)
    TextView tvVolume;
    @BindView(R.id.tv_flower)
    TextView tvFlower;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.tv_myvip)
    TextView tvMyvip;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.rl_myaccount)
    RelativeLayout rlMyaccount;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.rl_invitationcode)
    RelativeLayout rlInvitationcode;
    @BindView(R.id.image5)
    ImageView image5;
    @BindView(R.id.rl_myfriend)
    TextView rlMyfriend;
    @BindView(R.id.image6)
    ImageView image6;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.image7)
    ImageView image7;
    @BindView(R.id.rl_news)
    RelativeLayout rlNews;
    @BindView(R.id.te_setup)
    TextView teSetup;
    @BindView(R.id.tv_nightvision)
    TextView tvNightvision;
    @BindView(R.id.main_left_drawer_layout)
    RelativeLayout mainLeftDrawerLayout;
//    @BindView(R.id.main_right_drawer_layout)
//    RelativeLayout mainRightDrawerLayout;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mainDrawerLayout;
    @BindView(R.id.rl_sign)
    RelativeLayout rlSign;
    @BindView(R.id.rl_myfriends)
    RelativeLayout rlMyfriends;



    private BookReviewFragment bookReviewFragment;//书评
    private BookShlefFragment bookShlefFragment;//书架
    private ChoiceFragment choiceFragment;//精选
    private VolumeFragment volumeFragment;//领书卷
    private ClassifyFragment classifyFragment;//分类

    ClipboardManager manager;
//    List<Fragment> list = new List<Fragment>();

    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout drawerLayout;
    private RelativeLayout main_left_drawer_layout, main_right_drawer_layout;
    private FragmentTransaction ft;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = MainActivity.this;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        //侧滑控件布局
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        //设置菜单内容之外其他区域的背景色
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //左边菜单
        main_left_drawer_layout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
        //右边菜单
//        main_right_drawer_layout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);
        initEvent();
        initDefultView();
    }

    @Override
    protected void initData() {
        super.initData();
//            UserBean userBean = SharedPreferencesUtil.getObject(_context,SaveLocalData.USER_BEAN,UserBean.class);
//            userBean = (UserBean) bundle.getSerializable("userbean");
//            ToastUtil.showShort(_context,userBean.getId());
//        if (SharedPreferencesUtil.getParam(_context, SaveLocalData.USER_ID,null) != null){
//            ToastUtil.showShort(_context,userBean.getId());
//        }

        manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
        btnBookshlef.setOnClickListener(this);
        btnChoice.setOnClickListener(this);
        btnVolume.setOnClickListener(this);
        btnClassify.setOnClickListener(this);
        btnBookreview.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment
        switch (view.getId()) {
            case R.id.btn_bookshlef://书架
                if (bookShlefFragment == null) {
                    bookShlefFragment = new BookShlefFragment(_context, R.layout.fragment_bookshlef);
                    ft.add(R.id.content, bookShlefFragment);
                } else {
                    ft.show(bookShlefFragment);
                }

                break;
            case R.id.btn_choice://精选
                if (choiceFragment == null) {
                    choiceFragment = new ChoiceFragment(_context, R.layout.fragment_choice);
                    ft.add(R.id.content, choiceFragment);
                } else {
                    ft.show(choiceFragment);
                }
                break;
            case R.id.btn_volume://领书卷
                if (volumeFragment == null) {
                    volumeFragment = new VolumeFragment(_context, R.layout.fragment_volume);
                    ft.add(R.id.content, volumeFragment);
                } else {
                    ft.show(volumeFragment);
                }
                break;
            case R.id.btn_classify://分类
                if (classifyFragment == null) {
                    classifyFragment = new ClassifyFragment(_context, R.layout.fragment_classify);
                    ft.add(R.id.content, classifyFragment);
                } else {
                    ft.show(classifyFragment);
                }
                break;
            case R.id.btn_bookreview://书评
                if (bookReviewFragment == null) {
                    bookReviewFragment = new BookReviewFragment(_context, R.layout.fragmeng_bookreview);
                    ft.add(R.id.content, bookReviewFragment);
                } else {
                    ft.show(bookReviewFragment);
                }
                break;
        }
        ft.commit();   //提交事务
    }

    //隐藏fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (bookShlefFragment != null) {
            fragmentTransaction.hide(bookShlefFragment);
        }
        if (choiceFragment != null) {
            fragmentTransaction.hide(choiceFragment);
        }

        if (volumeFragment != null) {
            fragmentTransaction.hide(volumeFragment);
        }
        if (classifyFragment != null) {
            fragmentTransaction.hide(classifyFragment);
        }
        if (bookReviewFragment != null) {
            fragmentTransaction.hide(bookReviewFragment);
        }
    }

    //默认显示第一个fragment
    private void initDefultView() {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        choiceFragment = new ChoiceFragment(_context, R.layout.fragment_choice);
        ft.add(R.id.content, choiceFragment);
        ft.commit();
    }

    @Override
    public void setResult(Object... param) {
    }

    private void initEvent() {
        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, R.mipmap.ic_launcher, R.color.black, R.color.blue) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerbar);
    }

    //左边菜单开关事件
    public void openLeftLayout(View view) {
        if (drawerLayout.isDrawerOpen(main_left_drawer_layout)) {
            drawerLayout.closeDrawer(main_left_drawer_layout);
        } else {
            drawerLayout.openDrawer(main_left_drawer_layout);
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

    @OnClick({R.id.content, R.id.btn_bookshlef, R.id.btn_choice, R.id.btn_volume, R.id.btn_classify, R.id.btn_bookreview, R.id.rg_tab, R.id.iv_hean, R.id.tv_name, R.id.tv_vip, R.id.te_share, R.id.tv_volume, R.id.tv_flower, R.id.tv_sign, R.id.image1, R.id.tv_myvip, R.id.tv_recharge, R.id.image2, R.id.rl_myaccount, R.id.image4, R.id.rl_invitationcode, R.id.image5, R.id.rl_myfriend, R.id.image6, R.id.rl_collection, R.id.image7, R.id.rl_news, R.id.te_setup, R.id.tv_nightvision, R.id.main_left_drawer_layout, R.id.main_drawer_layout, R.id.rl_sign, R.id.rl_myfriends})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rg_tab:
                break;
            case R.id.iv_hean://点击头像更改个人资料
                startActivity(ModifyDataActivity.class, null);
                break;
            case R.id.tv_name://点击昵称更改个人资料
                startActivity(ModifyDataActivity.class, null);
                break;
            case R.id.tv_vip:
                break;
            case R.id.te_share://点击复制邀请码
                UserBean userBean = (UserBean) SharedPreferencesUtil.getObject(_context, SaveLocalData.USER_BEAN, UserBean.class);
                if (!StringUtil.isEmpty(userBean.getInvitecode())) {
                    ClipData clipData = ClipData.newPlainText("", userBean.getInvitecode());
                    manager.setPrimaryClip(clipData);
                }

                break;
            case R.id.tv_sign://邀请好友,点击回到领书卷页面
                FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
                FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
                hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment
                openLeftLayout(main_left_drawer_layout);

                if (volumeFragment == null) {
                    volumeFragment = new VolumeFragment(_context, R.layout.fragment_volume);
                    ft.add(R.id.content, volumeFragment);
                } else {
                    ft.show(volumeFragment);
                }
//                btnVolume.isChecked();
//                btnVolume.isSelected();
                btnVolume.setChecked(true);
                ft.commit();
                break;
            case R.id.tv_myvip://我的VIP
                openLeftLayout(main_left_drawer_layout);
                startActivity(VipActivity.class, null);
                break;
            case R.id.tv_recharge://充值
                openLeftLayout(main_left_drawer_layout);
                startActivity(RechargeActivity.class, null);
                break;
            case R.id.rl_myaccount://我的账户
                openLeftLayout(main_left_drawer_layout);
                startActivity(MyAccountActivity.class, null);
                break;
            case R.id.rl_invitationcode://填写邀请码
                openLeftLayout(main_left_drawer_layout);
                startActivity(InvitationCodeActivity.class, null);
                break;
            case R.id.rl_myfriends://我的好友
                openLeftLayout(main_left_drawer_layout);
                startActivity(MyFriendActivity.class, null);
                break;
            case R.id.rl_myfriend://我的好友
                break;
            case R.id.rl_collection://收藏
                openLeftLayout(main_left_drawer_layout);
                startActivity(CollectionActivity.class, null);
                break;
            case R.id.rl_sign://签到
                openLeftLayout(main_left_drawer_layout);
                startActivity(SignActivity.class, null);
                break;
            case R.id.rl_news://消息
                openLeftLayout(main_left_drawer_layout);
                startActivity(NewsActivity.class, null);
                break;
            case R.id.te_setup://设置
                openLeftLayout(main_left_drawer_layout);
                startActivity(SetUpActivity.class, null);
                break;
            case R.id.tv_nightvision://夜视
                openLeftLayout(main_left_drawer_layout);
                ToastUtil.showShort(_context, DateUtile.GetYear() + "年" + DateUtile.GetMonth() + "月" + DateUtile.GetDay() + "天" + DateUtile.GetWay() + "星期");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}


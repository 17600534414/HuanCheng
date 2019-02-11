package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.BookBean;
import com.huancheng.reader.bean.BoxBean;
import com.huancheng.reader.bean.UserBean;
import com.huancheng.reader.common.SaveLocalData;
import com.huancheng.reader.ui.activity.sub.BookInfoActivity;
import com.huancheng.reader.ui.activity.sub.FinishingBookActivity;
import com.huancheng.reader.ui.activity.sub.ModifyDataActivity;
import com.huancheng.reader.ui.activity.sub.SignActivity;
import com.huancheng.reader.ui.adapter.sub.BookShelfAdapter;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.SharedPreferencesUtil;
import com.huancheng.reader.util.ToastUtil;
import com.huancheng.reader.wegiht.GlideImageLoader;
import com.huancheng.reader.wegiht.OpenLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import okhttp3.Call;

/**
 * Created by admin on 2018/12/3.
 */

@SuppressLint("ValidFragment")
public class BookShlefFragment extends BaseFragment{

    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.banner_bookshelf)
    Banner bannerBookshelf;
    @BindView(R.id.iv_my)
    ImageView ivMy;
    @BindView(R.id.iv_book)
    ImageView ivBook;
    @BindView(R.id.iv_sign)
    ImageView ivSign;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rv_shelfbook)
    RecyclerView rvShelfbook;

    private Banner banner_shlef;
    private ImageView iv_banner;
    private List list = new ArrayList();
    private List list1 = new ArrayList();
    private BookShelfAdapter bookShelfAdapter = new BookShelfAdapter();

    private int minute ;//这是分钟
    private int second = 0;//这是分钟后面的秒数。这里是以30分钟为例的，所以，minute是30，second是0
    private int hour = 3;
    private Timer timer;
    private TimerTask timerTask;


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (minute == 0) {
                if (second == 0) {
                    tvTime.setText("结束");
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    if (timerTask != null) {
                        timerTask = null;
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        tvTime.setText("0" + minute + ":" + second);
                    } else {
                        tvTime.setText("0" + minute + ":0" + second);
                    }
                }
            } else {
                if (second == 0) {
                    second = 59;
                    minute--;
                    if (minute >= 10) {
                        tvTime.setText(minute + ":" + second);
                    } else {
                        tvTime.setText("0" + minute + ":" + second);
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        if (minute >= 10) {
                            tvTime.setText(minute + ":" + second);
                        } else {
                            tvTime.setText("0" + minute + ":" + second);
                        }
                    } else {
                        if (minute >= 10) {
                            tvTime.setText(minute + ":0" + second);
                        } else {
                            tvTime.setText("0" + minute + ":0" + second);
                        }
                    }
                }
            }
        }
    };

    @SuppressLint("ValidFragment")
    public BookShlefFragment(Context context, int resId) {
        super(context, resId);
        _context = context;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        ButterKnife.bind(this,v);
        iv_banner = v.findViewById(R.id.iv_banner);
        banner_shlef = (Banner) v.findViewById(R.id.banner_bookshelf);
        list1.add(R.mipmap.book_lead);
        list1.add(R.mipmap.book_me);
        list1.add(R.mipmap.book_seek);
        startBanner();//轮播图
        UserBean userBean = SharedPreferencesUtil.getObject(_context, SaveLocalData.USER_BEAN, UserBean.class);

        if (userBean.getStates().equals("0")){
            minute = 10;
                if (minute == 0 && second == 10) {
                    tvTime.setText("" + minute + ":" + second);
                } else {
                    tvTime.setText(minute + ":" + second);
                }
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    }
                };
                timer = new Timer();
                timer.schedule(timerTask, 0, 1000);
            } else if (userBean.getStates().equals("1")){
            minute = 60;
            if (minute == 0 && second == 10) {
                tvTime.setText("" + minute + ":" + second);
            } else {
                tvTime.setText(minute + ":" + second);
            }
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, 0, 1000);
            } else if (userBean.getStates().equals("2")){
            minute = 180;
            if (minute == 0 && second == 10) {
                tvTime.setText("" + minute + ":" + second);
            } else {
                tvTime.setText(minute + ":" + second);
            }
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, 0, 1000);
            }

    }

    @Override
    protected void initData() {

        list.add(new BookBean(R.mipmap.book_book,"第一本","第一本","0"));
        list.add(new BookBean(R.mipmap.book_book,"第二本","第二本","0"));
        list.add(new BookBean(R.mipmap.book_book,"第三本","第三本","1"));
        list.add(new BookBean(R.mipmap.book_book,"第四本","第四本","0"));
        list.add(new BookBean(R.mipmap.book_book,"第五本","第五本","1"));
        list.add(new BookBean(R.mipmap.book_add,"","","2"));
        rvShelfbook.setLayoutManager (new GridLayoutManager(_context,3,GridLayoutManager.VERTICAL,false));
        bookShelfAdapter = new BookShelfAdapter(_context,list,1);
        rvShelfbook.setAdapter(bookShelfAdapter);
        bookShelfAdapter.setOnItemClickListener(new BookShelfAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (position < 5){

                    startActivity(BookInfoActivity.class,null);
                } else {
//                    startActivity();//逛书城
                    ToastUtil.showShort(_context,"这里要跳到逛书城页面");
                }
            }
        });
        bookShelfAdapter.setOnItemLongClickListener(new BookShelfAdapter.OnItemLongClickListener() {
            @Override
            public void onClick(int position) {
                if (position < 5){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (Serializable) list);
                    startActivity(FinishingBookActivity.class,bundle);
                }

            }
        });
    }

    @Override
    protected void BindComponentEvent() {


    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
    @OnClick({R.id.iv_my, R.id.iv_book, R.id.iv_sign, R.id.iv_search, R.id.iv_add, R.id.tv_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my://我的
                OpenLayout openLayout = new OpenLayout();
//                    openLayout.openLeftLayout(R.layout.main_left_drawer_layout);
                break;
            case R.id.iv_book://书卷
                break;
            case R.id.iv_sign://签到
                startActivity(SignActivity.class, null);
                break;
            case R.id.iv_search://搜索
                break;
            case R.id.iv_add://导入
                break;
            case R.id.tv_time://宝箱
                UserBean userBean = SharedPreferencesUtil.getObject(_context, SaveLocalData.USER_BEAN, UserBean.class);
                if (userBean.getStates().equals("0")){
                    long time=System.currentTimeMillis();
                    long time1 = (long)SharedPreferencesUtil.getParam(_context,SaveLocalData.TIME,0L);
                    if ((time - time1) <600000){
                        ToastUtil.showShort(_context,"未到领取宝箱的时间");
                    } else {
                        eceiveTreasureChest("5");
                    }
                }
                if (userBean.getStates().equals("1")){
                    long time=System.currentTimeMillis();
                    long time1 = (long)SharedPreferencesUtil.getParam(_context,SaveLocalData.TIME,0L);
                    if ((time - time1) <3600000){
                        ToastUtil.showShort(_context,"未到领取宝箱的时间");
                    } else {
                        eceiveTreasureChest("10");
                    }
                }
                if (userBean.getStates().equals("2")){
                    long time=System.currentTimeMillis();
                    long time1 = (long)SharedPreferencesUtil.getParam(_context,SaveLocalData.TIME,0L);
                    if ((time - time1) <10800000){
                        ToastUtil.showShort(_context,"未到领取宝箱的时间");
                    } else {
                        eceiveTreasureChest("20");
                    }
                }if (userBean.getStates().equals("3")){
                    ToastUtil.showShort(_context,"所有宝箱已经领取完成");
            }

                break;
            case R.id.iv_book_add://逛书城
                break;
        }
    }



    //轮播图
    private void startBanner(){
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        banner_shlef.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        banner_shlef.setIndicatorGravity(BannerConfig.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
//        banner_shlef.setBannerTitle(titles);
        //图片集合
        banner_shlef.setImages(list1);
//        banner_shlef.setBannerTitles(list1);

        //设置是否自动轮播（不设置则默认自动）
        banner_shlef.isAutoPlay(true) ;

        //设置轮播图片间隔时间（不设置默认为2000）
        banner_shlef.setDelayTime(3000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //设置图片加载器
//        Glide.with(_context).load(list).into(iv_banner);
        banner_shlef.setImageLoader(new GlideImageLoader());
        //设置点击事件，下标是从1开始
        banner_shlef.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showShort(_context,"你点击了"+position);
            }
        });
        banner_shlef.start();
    }


    //领取宝箱
    private void eceiveTreasureChest(String num){
        UserBean userBean = SharedPreferencesUtil.getObject(_context, SaveLocalData.USER_BEAN, UserBean.class);
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.ECEIVE_TREASURE_CHEST)
                .addParams("userId", userBean.getId())
                .addParams("num", num)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context,"领取宝箱失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        BoxBean boxBean = GsonUtil.getBeanFromJson(response,BoxBean.class);
                        int code = Integer.parseInt(boxBean.getCode());
                        switch (code){
                            case 0:
                                ToastUtil.showShort(_context,boxBean.getMsg());
                                UserBean userBean1 = SharedPreferencesUtil.getObject(_context,SaveLocalData.USER_BEAN,UserBean.class);
                                userBean1.setStates(boxBean.getStates());
                                SharedPreferencesUtil.setObject(_context,SaveLocalData.USER_BEAN,userBean1);
                                break;
                            case 500:
                                ToastUtil.showShort(_context,boxBean.getMsg());
                                break;
                        }
                    }
                });
    }

    //关掉定时器
    @Override
    public void onDestroyView() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask = null;
        }
        minute = -1;
        second = -1;
        super.onDestroyView();
    }

}

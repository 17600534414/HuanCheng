package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextSwitcher;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.sub.FindActivity;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.util.ToastUtil;
import com.huancheng.reader.wegiht.AutoVerticalScrollTextView;
import com.huancheng.reader.wegiht.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/12/24.
 */

@SuppressLint("ValidFragment")
public class FindFragment extends BaseFragment {

    @BindView(R.id.iv_banner_find)
    ImageView ivBannerFind;
    @BindView(R.id.banner_find)
    Banner bannerFind;
    @BindView(R.id.tv_vertical_advertisement_find)
    TextSwitcher tvVerticalAdvertisementFind;
    @BindView(R.id.rv_find)
    RecyclerView rvFind;
    /**
     * 纵向自动轮播广告
     */
    private int number =0;
    private boolean isRunning=true;
    private String[] strings={"我的剑，就是你的剑!","俺也是从石头里蹦出来得!","我用双手成就你的梦想!","人在塔在!","犯我德邦者，虽远必诛!","我会让你看看什么叫残忍!","我的大刀早已饥渴难耐了!"};
    private String string="我的剑，就是你的剑!   俺也是从石头里蹦出来得!    我用双手成就你的梦想!    人在塔在!    犯我德邦者，虽远必诛!    我会让你看看什么叫残忍!    我的大刀早已饥渴难耐了!";
    private AutoVerticalScrollTextView verticalScrollTV;
    List list = new ArrayList();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                verticalScrollTV.next();
                number++;
                verticalScrollTV.setText(strings[number%strings.length]);
            }

        }
    };
    public FindFragment(Context context, int resId) {
        super(context, resId);
        _context = context;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        ButterKnife.bind(this,v);
        list.add(R.mipmap.book_lead);
        list.add(R.mipmap.book_me);
        list.add(R.mipmap.book_seek);
        startBanner();
        verticalScrollTV = (AutoVerticalScrollTextView) v.findViewById(R.id.tv_vertical_advertisement_find);
        verticalScrollTV.setText(strings[0]);

        new Thread(){
            @Override
            public void run() {
                while (isRunning){
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(199);
                }
            }
        }.start();

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
    //轮播图
    private void startBanner(){
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        bannerFind.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        bannerFind.setIndicatorGravity(BannerConfig.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
//        banner_shlef.setBannerTitle(titles);
        //图片集合
        bannerFind.setImages(list);
//        banner_shlef.setBannerTitles(list1);

        //设置是否自动轮播（不设置则默认自动）
        bannerFind.isAutoPlay(true) ;

        //设置轮播图片间隔时间（不设置默认为2000）
        bannerFind.setDelayTime(3000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //设置图片加载器
//        Glide.with(_context).load(list).into(iv_banner);
        bannerFind.setImageLoader(new GlideImageLoader());
        //设置点击事件，下标是从1开始
        bannerFind.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showShort(_context,"你点击了"+position);
            }
        });
        bannerFind.start();
    }
}

package com.huancheng.reader.ui.activity.sub;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.huancheng.reader.HomePageBookBean;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.EndBookAllBean;
import com.huancheng.reader.bean.TypePublicBean;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.ui.adapter.sub.HomePageNewAdapter;
import com.huancheng.reader.ui.adapter.sub.HomePageTrumpAdapter;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.ToastUtil;
import com.huancheng.reader.wegiht.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class EndBookAllActivity extends BaseBussActivity {


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.iv_endbookallbanner)
    ImageView ivEndbookallbanner;
    @BindView(R.id.banner_endbookallbanner)
    Banner bannerEndbookallbanner;
    @BindView(R.id.rv_endbookall)
    RecyclerView rvEndbookall;

    List list = new ArrayList();
    List<TypePublicBean> list1 = new ArrayList<>();
    HomePageTrumpAdapter homePageNewAdapter;
    TypePublicBean typePublicBean = new TypePublicBean();

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = EndBookAllActivity.this;
        setContentView(R.layout.activity_end_book_all);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("完结");
        tvBack.setTextColor(Color.WHITE);


    }

    @Override
    protected void initData() {
        super.initData();
        list.add(R.mipmap.book_lead);
        list.add(R.mipmap.book_me);
        list.add(R.mipmap.book_seek);

        //滑动冲突
//        rvEndbookall.setNestedScrollingEnabled(false);
        startBanner1();
        getHomePageBook();
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }

    @OnClick({R.id.iv_left_title_bar, R.id.iv_right_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.iv_right_title_bar:
                break;
        }
    }
    //轮播图
    private void startBanner1() {
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        bannerEndbookallbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        bannerEndbookallbanner.setIndicatorGravity(BannerConfig.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
//        banner_shlef.setBannerTitle(titles);
        //图片集合
        bannerEndbookallbanner.setImages(list);
//        banner_shlef.setBannerTitles(list1);

        //设置是否自动轮播（不设置则默认自动）
        bannerEndbookallbanner.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
        bannerEndbookallbanner.setDelayTime(2000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //设置图片加载器
//        Glide.with(_context).load(list).into(iv_banner);
        bannerEndbookallbanner.setImageLoader(new GlideImageLoader());
        //设置点击事件，下标是从1开始
        bannerEndbookallbanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showShort(_context, "你点击了" + position);
            }
        });
        bannerEndbookallbanner.start();
    }

    //获取完结类型书籍数据
    private void getHomePageBook() {
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST + HttpUtil.BOOK_END_ALL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context, "获取失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        String code = GsonUtil.getJsonFromKey(response,"code");
//                        TypePublicBookBean typePublicBookBean = GsonUtil.getBeanFromJson(response,TypePublicBookBean.class);
                        EndBookAllBean endBookAllBean = GsonUtil.getBeanFromJson(response, EndBookAllBean.class);
                        String code = endBookAllBean.getCode();
                        Log.d("code", code);
                        switch (Integer.parseInt(code)) {
                            case 0:
                                String book = GsonUtil.getJsonFromKey(response, "book");

                                list1 = GsonUtil.getListFromJson(book, new TypeToken<List<TypePublicBean>>() {
                                });

                                rvEndbookall.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageNewAdapter = new HomePageTrumpAdapter(_context,list1,1);
                                rvEndbookall.setAdapter(homePageNewAdapter);

                                homePageNewAdapter.setOnItemClickListener(new HomePageTrumpAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = list1.get(position);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });

                                break;
                            case 500:
                                break;
                        }
                    }
                });

    }
}

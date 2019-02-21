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

public class WomanChannelActivity extends BaseBussActivity {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.iv_womanchannelbanner)
    ImageView ivWomanchannelbanner;
    @BindView(R.id.banner_womanchannel)
    Banner bannerWomanchannel;
    @BindView(R.id.tv_woman_modernromance)
    TextView tvWomanModernromance;
    @BindView(R.id.rv_woman_modernromance)
    RecyclerView rvWomanModernromance;
    @BindView(R.id.tv_woman_ancientromance)
    TextView tvWomanAncientromance;
    @BindView(R.id.rv_woman_ancientromance)
    RecyclerView rvWomanAncientromance;
    @BindView(R.id.tv_woman_fantasyromance)
    TextView tvWomanFantasyromance;
    @BindView(R.id.rv_woman_fantasyromance)
    RecyclerView rvWomanFantasyromance;
    @BindView(R.id.tv_woman_youth)
    TextView tvWomanYouth;
    @BindView(R.id.rv_woman_youth)
    RecyclerView rvWomanYouth;
    @BindView(R.id.tv_woman_girlsuspense)
    TextView tvWomanGirlsuspense;
    @BindView(R.id.rv_woman_girlsuspense)
    RecyclerView rvWomanGirlsuspense;
    @BindView(R.id.tv_woman_purelove)
    TextView tvWomanPurelove;
    @BindView(R.id.rv_woman_purelovee)
    RecyclerView rvWomanPurelovee;

    List list = new ArrayList();
    List<List<TypePublicBean>> list1 = new ArrayList<List<TypePublicBean>>();
    private List<TypePublicBean> modernromancelist,ancientromancelist,fantasyromancelist,youthlist,girlsuspenselist,pureloveelist;
    private HomePageNewAdapter homePageNewAdapter2,homePageNewAdapter4,homePageNewAdapter6;
    private HomePageTrumpAdapter homePageTrumpAdapter1,homePageTrumpAdapter3,homePageTrumpAdapter5;
    TypePublicBean typePublicBean = new TypePublicBean();
    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = WomanChannelActivity.this;
        setContentView(R.layout.activity_woman_channel);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("女生频道");
        tvBack.setTextColor(Color.WHITE);
        list.add(R.mipmap.book_lead);
        list.add(R.mipmap.book_me);
        list.add(R.mipmap.book_seek);

        //滑动冲突
        rvWomanModernromance.setNestedScrollingEnabled(false);
        rvWomanAncientromance.setNestedScrollingEnabled(false);
        rvWomanFantasyromance.setNestedScrollingEnabled(false);
        rvWomanYouth.setNestedScrollingEnabled(false);
        rvWomanGirlsuspense.setNestedScrollingEnabled(false);
        rvWomanPurelovee.setNestedScrollingEnabled(false);

        startBanner1();

    }

    @Override
    protected void initData() {
        super.initData();
        getHomePageBook();
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }


    @OnClick({R.id.iv_left_title_bar, R.id.iv_right_title_bar, R.id.tv_woman_modernromance, R.id.tv_woman_ancientromance, R.id.tv_woman_fantasyromance, R.id.tv_woman_youth, R.id.tv_woman_girlsuspense, R.id.tv_woman_purelove})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.iv_right_title_bar:
                break;
            case R.id.tv_woman_modernromance://现代言情更多
                Bundle bundle1 = new Bundle();
                bundle1.putString("manbookmore","都市言情");
                startActivity(ManBookMoreActivity.class,bundle1);
                break;
            case R.id.tv_woman_ancientromance://古装言情更多
                Bundle bundle2 = new Bundle();
                bundle2.putString("manbookmore","古装言情");
                startActivity(ManBookMoreActivity.class,bundle2);
                break;
            case R.id.tv_woman_fantasyromance://幻想言情更多
                Bundle bundle3 = new Bundle();
                bundle3.putString("manbookmore","幻想言情");
                startActivity(ManBookMoreActivity.class,bundle3);
                break;
            case R.id.tv_woman_youth://浪漫青春更多
                Bundle bundle4 = new Bundle();
                bundle4.putString("manbookmore","浪漫青春");
                startActivity(ManBookMoreActivity.class,bundle4);
                break;
            case R.id.tv_woman_girlsuspense://女生悬疑更多
                Bundle bundle5 = new Bundle();
                bundle5.putString("manbookmore","悬疑小说");
                startActivity(ManBookMoreActivity.class,bundle5);
                break;
            case R.id.tv_woman_purelove://纯爱小说更多
                Bundle bundle6 = new Bundle();
                bundle6.putString("manbookmore","情感小说");
                startActivity(ManBookMoreActivity.class,bundle6);
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
        bannerWomanchannel.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        bannerWomanchannel.setIndicatorGravity(BannerConfig.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
//        banner_shlef.setBannerTitle(titles);
        //图片集合
        bannerWomanchannel.setImages(list);
//        banner_shlef.setBannerTitles(list1);

        //设置是否自动轮播（不设置则默认自动）
        bannerWomanchannel.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
        bannerWomanchannel.setDelayTime(2000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //设置图片加载器
//        Glide.with(_context).load(list).into(iv_banner);
        bannerWomanchannel.setImageLoader(new GlideImageLoader());
        //设置点击事件，下标是从1开始
        bannerWomanchannel.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showShort(_context, "你点击了" + position);
            }
        });
        bannerWomanchannel.start();
    }

    //获取女频各类型数据
    private void getHomePageBook() {
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST + HttpUtil.BOOK_WOMAN_TYPE)
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
                        HomePageBookBean homePageBookBean = GsonUtil.getBeanFromJson(response, HomePageBookBean.class);
                        String code = homePageBookBean.getCode();
                        Log.d("code", code);
                        switch (Integer.parseInt(code)) {
                            case 0:
                                String book = GsonUtil.getJsonFromKey(response, "book");
                                list1 = GsonUtil.getListFromJson(book, new TypeToken<List<List<TypePublicBean>>>() {
                                });
//                                Log.d("book", book);
//                                Log.d("list", list1.toString());

//                                list = GsonUtil.getListFromJson(book,new TypeToken<List<TypePublicBean>>(){});

                                modernromancelist = list1.get(0);//现代言情
                                ancientromancelist = list1.get(1);//古装言情
                                fantasyromancelist = list1.get(2);//幻想言情
                                youthlist = list1.get(3);//浪漫青春
                                girlsuspenselist = list1.get(4);//女生悬疑
                                pureloveelist = list1.get(5);//纯爱小说


////                            //1
                                rvWomanModernromance.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageTrumpAdapter1 = new HomePageTrumpAdapter(_context,modernromancelist,1);
                                rvWomanModernromance.setAdapter(homePageTrumpAdapter1);

                                //2
                                rvWomanAncientromance.setLayoutManager (new GridLayoutManager(_context,1, GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter2 = new HomePageNewAdapter(_context,ancientromancelist,2);
                                rvWomanAncientromance.setAdapter(homePageNewAdapter2);

                                //3
                                rvWomanFantasyromance.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageTrumpAdapter3 = new HomePageTrumpAdapter(_context,fantasyromancelist,1);
                                rvWomanFantasyromance.setAdapter(homePageTrumpAdapter3);

                                //4
                                rvWomanYouth.setLayoutManager (new GridLayoutManager(_context,1, GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter4 = new HomePageNewAdapter(_context,youthlist,2);
                                rvWomanYouth.setAdapter(homePageNewAdapter4);
                                //5
                                rvWomanGirlsuspense.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageTrumpAdapter5 = new HomePageTrumpAdapter(_context,girlsuspenselist,1);
                                rvWomanGirlsuspense.setAdapter(homePageTrumpAdapter5);

                                //6
                                rvWomanPurelovee.setLayoutManager (new GridLayoutManager(_context,1, GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter6 = new HomePageNewAdapter(_context,pureloveelist,2);
                                rvWomanPurelovee.setAdapter(homePageNewAdapter6);
                                homePageTrumpAdapter1.setOnItemClickListener(new HomePageTrumpAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = modernromancelist.get(position);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageNewAdapter2.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = ancientromancelist.get(position);//人气推荐
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageTrumpAdapter3.setOnItemClickListener(new HomePageTrumpAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = fantasyromancelist.get(position); //主编推荐
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageNewAdapter4.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = youthlist.get(position);//畅读王牌
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
//
                                homePageTrumpAdapter5.setOnItemClickListener(new HomePageTrumpAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = girlsuspenselist.get(position);//畅读必看
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);

                                    }
                                });
                                homePageNewAdapter6.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = pureloveelist.get(position);//畅读王牌
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

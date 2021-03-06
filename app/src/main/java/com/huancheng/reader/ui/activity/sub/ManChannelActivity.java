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

public class ManChannelActivity extends BaseBussActivity {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.iv_manchannelbanner)
    ImageView ivManchannelbanner;
    @BindView(R.id.banner_manchannel)
    Banner banner1;
    @BindView(R.id.tv_man_urban)
    TextView tvManUrban;
    @BindView(R.id.rv_man_urban)
    RecyclerView rvManUrban;
    @BindView(R.id.tv_man_fantasy)
    TextView tvManFantasy;
    @BindView(R.id.rv_man_fantasy)
    RecyclerView rvManFantasy;
    @BindView(R.id.tv_man_knigh_errant)
    TextView tvManKnighErrant;
    @BindView(R.id.rv_man_knigh_errant)
    RecyclerView rvManKnighErrant;
    @BindView(R.id.tv_man_history)
    TextView tvManHistory;
    @BindView(R.id.rv_man_history)
    RecyclerView rvManHistory;
    @BindView(R.id.tv_man_novel)
    TextView tvManNovel;
    @BindView(R.id.rv_man_novel)
    RecyclerView rvManNovel;
    @BindView(R.id.tv_man_suspense)
    TextView tvManSuspense;
    @BindView(R.id.rv_man_suspense)
    RecyclerView rvManSuspense;
    @BindView(R.id.tv_man_game)
    TextView tvManGame;
    @BindView(R.id.rv_man_game)
    RecyclerView rvManGame;

    List list = new ArrayList();
    List<List<TypePublicBean>> list1 = new ArrayList<List<TypePublicBean>>();
    private List<TypePublicBean> urbanlist,fantasylist,errantlist,historylist,novellist,suspenselist,gamelist;
    private HomePageNewAdapter homePageNewAdapter2,homePageNewAdapter4,homePageNewAdapter6;
    private HomePageTrumpAdapter homePageTrumpAdapter1,homePageTrumpAdapter3,homePageTrumpAdapter5,homePageTrumpAdapter7;
    TypePublicBean typePublicBean = new TypePublicBean();

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = ManChannelActivity.this;
        setContentView(R.layout.activity_man_channel);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("男生频道");
        tvBack.setTextColor(Color.WHITE);
        list.add(R.mipmap.book_lead);
        list.add(R.mipmap.book_me);
        list.add(R.mipmap.book_seek);
        startBanner1();
        //滑动冲突
        rvManUrban.setNestedScrollingEnabled(false);
        rvManFantasy.setNestedScrollingEnabled(false);
        rvManKnighErrant.setNestedScrollingEnabled(false);
        rvManHistory.setNestedScrollingEnabled(false);
        rvManNovel.setNestedScrollingEnabled(false);
        rvManSuspense.setNestedScrollingEnabled(false);
        rvManGame.setNestedScrollingEnabled(false);
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

    @OnClick({R.id.tv_man_urban, R.id.tv_man_fantasy, R.id.tv_man_knigh_errant,
            R.id.tv_man_history, R.id.tv_man_novel, R.id.tv_man_suspense, R.id.tv_man_game,
            R.id.iv_left_title_bar, R.id.iv_right_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.iv_right_title_bar:
                break;
            case R.id.tv_man_urban://现代都市更多
                Bundle bundle1 = new Bundle();
                bundle1.putString("manbookmore","都市小说");
                startActivity(ManBookMoreActivity.class,bundle1);
                break;
            case R.id.tv_man_fantasy://玄幻奇幻更多
                Bundle bundle2 = new Bundle();
                bundle2.putString("manbookmore","玄幻奇幻");
                startActivity(ManBookMoreActivity.class,bundle2);
                break;
            case R.id.tv_man_knigh_errant://武侠仙侠更多
                Bundle bundle3 = new Bundle();
                bundle3.putString("manbookmore","仙侠武侠");
                startActivity(ManBookMoreActivity.class,bundle3);
                break;
            case R.id.tv_man_history://军事历史更多
                Bundle bundle4 = new Bundle();
                bundle4.putString("manbookmore","历史军事");
                startActivity(ManBookMoreActivity.class,bundle4);
                break;
            case R.id.tv_man_novel://科幻末日更多
                Bundle bundle5 = new Bundle();
                bundle5.putString("manbookmore","科幻末世");
                startActivity(ManBookMoreActivity.class,bundle5);
                break;
            case R.id.tv_man_suspense://悬疑小说更多
                Bundle bundle6 = new Bundle();
                bundle6.putString("manbookmore","悬疑小说");
                startActivity(ManBookMoreActivity.class,bundle6);
                break;
            case R.id.tv_man_game://游戏竞技更多
                Bundle bundle7 = new Bundle();
                bundle7.putString("manbookmore","游戏竞技");
                startActivity(ManBookMoreActivity.class,bundle7);
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
        banner1.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        banner1.setIndicatorGravity(BannerConfig.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
//        banner_shlef.setBannerTitle(titles);
        //图片集合
        banner1.setImages(list);
//        banner_shlef.setBannerTitles(list1);

        //设置是否自动轮播（不设置则默认自动）
        banner1.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
        banner1.setDelayTime(2000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //设置图片加载器
//        Glide.with(_context).load(list).into(iv_banner);
        banner1.setImageLoader(new GlideImageLoader());
        //设置点击事件，下标是从1开始
        banner1.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.showShort(_context, "你点击了" + position);
            }
        });
        banner1.start();
    }

    //获取男频各类型数据
    private void getHomePageBook() {
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST + HttpUtil.BOOK_MAN_TYPE)
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

//                                rvHomePage.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
//                                homePageAdapter = new HomePageAdapter(_context,list);
//                                rvHomePage.setAdapter(homePageAdapter);
                                urbanlist = list1.get(0);//都市小说
                                fantasylist = list1.get(1);//玄幻奇幻
                                errantlist = list1.get(2);//武侠仙侠
                                historylist = list1.get(3);//历史军事
                                novellist = list1.get(4);//科幻末世
                                suspenselist = list1.get(5);//悬疑小说
                                gamelist = list1.get(6);//游戏竞技



////
////                            //1
                                rvManUrban.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageTrumpAdapter1 = new HomePageTrumpAdapter(_context,urbanlist,1);
                                rvManUrban.setAdapter(homePageTrumpAdapter1);

                                //2
                                rvManFantasy.setLayoutManager (new GridLayoutManager(_context,1, GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter2 = new HomePageNewAdapter(_context,fantasylist,2);
                                rvManFantasy.setAdapter(homePageNewAdapter2);

                                //3
                                rvManKnighErrant.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageTrumpAdapter3 = new HomePageTrumpAdapter(_context,errantlist,1);
                                rvManKnighErrant.setAdapter(homePageTrumpAdapter3);

                                //4
                                rvManHistory.setLayoutManager (new GridLayoutManager(_context,1, GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter4 = new HomePageNewAdapter(_context,historylist,2);
                                rvManHistory.setAdapter(homePageNewAdapter4);
                                //5
                                rvManNovel.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageTrumpAdapter5 = new HomePageTrumpAdapter(_context,novellist,1);
                                rvManNovel.setAdapter(homePageTrumpAdapter5);

                                //6
                                rvManSuspense.setLayoutManager (new GridLayoutManager(_context,1, GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter6 = new HomePageNewAdapter(_context,suspenselist,2);
                                rvManSuspense.setAdapter(homePageNewAdapter6);
                                //7
                                rvManGame.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageTrumpAdapter7 = new HomePageTrumpAdapter(_context,gamelist,1);
                                rvManGame.setAdapter(homePageTrumpAdapter7);



                                homePageTrumpAdapter1.setOnItemClickListener(new HomePageTrumpAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = urbanlist.get(position);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageNewAdapter2.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = fantasylist.get(position);//人气推荐
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageTrumpAdapter3.setOnItemClickListener(new HomePageTrumpAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = errantlist.get(position); //主编推荐
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageNewAdapter4.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = historylist.get(position);//畅读王牌
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
                                        typePublicBean = novellist.get(position);//畅读必看
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);

                                    }
                                });
                                homePageNewAdapter6.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = suspenselist.get(position);//畅读王牌
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
//
                                homePageTrumpAdapter7.setOnItemClickListener(new HomePageTrumpAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = gamelist.get(position);//畅读必看
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

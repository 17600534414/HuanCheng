package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.huancheng.reader.HomePageBookBean;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.TypePublicBean;
import com.huancheng.reader.bean.TypePublicBookBean;
import com.huancheng.reader.ui.activity.sub.BookClassIficationActivity;
import com.huancheng.reader.ui.activity.sub.BookInfoActivity;
import com.huancheng.reader.ui.activity.sub.BookListActivity;
import com.huancheng.reader.ui.activity.sub.EndBookActivity;
import com.huancheng.reader.ui.activity.sub.EndBookAllActivity;
import com.huancheng.reader.ui.activity.sub.ManActivity;
import com.huancheng.reader.ui.activity.sub.ManChannelActivity;
import com.huancheng.reader.ui.activity.sub.NewBookMoreActivity;
import com.huancheng.reader.ui.activity.sub.SearchActivity;
import com.huancheng.reader.ui.activity.sub.WomanActivity;
import com.huancheng.reader.ui.activity.sub.WomanChannelActivity;
import com.huancheng.reader.ui.adapter.sub.BookShelfAdapter;
import com.huancheng.reader.ui.adapter.sub.HomePageAdapter;
import com.huancheng.reader.ui.adapter.sub.HomePageNewAdapter;
import com.huancheng.reader.ui.adapter.sub.HomePageTrumpAdapter;
import com.huancheng.reader.ui.adapter.sub.ManPublicAdapter;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.ToastUtil;
import com.huancheng.reader.wegiht.AutoHorizontalScrollTextView;
import com.huancheng.reader.wegiht.AutoVerticalScrollTextView;
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

/**
 * Created by admin on 2018/12/3.
 */

@SuppressLint("ValidFragment")
public class ChoiceFragment extends BaseFragment {

    @BindView(R.id.iv_banner1)
    ImageView ivBanner1;
    @BindView(R.id.banner1)
    Banner banner1;
    @BindView(R.id.iv_my)
    ImageView ivMy;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_man)
    ImageView ivMan;
    @BindView(R.id.iv_woman)
    ImageView ivWoman;
    @BindView(R.id.iv_endbook)
    ImageView ivEndbook;
    @BindView(R.id.iv_list)
    ImageView ivList;
    @BindView(R.id.iv_banner2)
    ImageView ivBanner2;
    @BindView(R.id.banner2)
    Banner banner2;
    @BindView(R.id.tv_vertical_advertisement)
    TextSwitcher tvVerticalAdvertisement;
    @BindView(R.id.tv_newbook)
    TextView tvNewbook;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_trump)
    TextView tvTrump;
    @BindView(R.id.tv_must_see)
    TextView tvMustsee;
    @BindView(R.id.rv_newbook)
    RecyclerView rvNewbook;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.rv_edit)
    RecyclerView rvEdit;
    @BindView(R.id.rv_trump)
    RecyclerView rvTrump;
    @BindView(R.id.rv_must_see)
    RecyclerView rvMustsee;
    @BindView(R.id.iv_my_img_home)
    ImageView ivMyimghome;
    @BindView(R.id.iv_book_home)
    ImageView ivBookhome;
    @BindView(R.id.iv_book_name_home)
    ImageView ivBoojnamehome;
    @BindView(R.id.tv_my_name_home)
    TextView tvMynamehome;
    @BindView(R.id.tv_book_name_home)
    TextView tvBooknamehome;
    @BindView(R.id.tv_book_introduce_home)
    TextView tvBookintroducehome;
    @BindView(R.id.tv_book_type_home)
    TextView tvBooktypehome;
    @BindView(R.id.ll_recommend)
    LinearLayout llRecommend;

//    @BindView(R.id.rv_home_page)
//    RecyclerView rvHomePage;

    /**
     * 纵向自动轮播广告
     */
    private int number =0;
    private boolean isRunning=true;
    private String[] strings={"我的剑，就是你的剑!","俺也是从石头里蹦出来得!","我用双手成就你的梦想!","人在塔在!","犯我德邦者，虽远必诛!","我会让你看看什么叫残忍!","我的大刀早已饥渴难耐了!"};
    private String string="我的剑，就是你的剑!   俺也是从石头里蹦出来得!    我用双手成就你的梦想!    人在塔在!    犯我德邦者，虽远必诛!    我会让你看看什么叫残忍!    我的大刀早已饥渴难耐了!";
    private AutoVerticalScrollTextView verticalScrollTV;
    private AutoHorizontalScrollTextView horizontalScrollTV;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                verticalScrollTV.next();
                number++;
                verticalScrollTV.setText(strings[number%strings.length]);
            }
//            if (msg.what == 199) {
////                horizontalScrollTV.isFocused();
////                number++;
//                horizontalScrollTV.setText(strings[number%strings.length]);
//            }

        }
    };

    private List<TypePublicBean> mustSeelist,newBooklist,recommendlist,editlist,trumplist;
    List list = new ArrayList();
    List<List<TypePublicBean>> list1 = new ArrayList<List<TypePublicBean>>();
    private HomePageNewAdapter homePageNewAdapter1,homePageNewAdapter2,homePageNewAdapter3,homePageNewAdapter5;
    HomePageTrumpAdapter homePageNewAdapter4;
    TypePublicBean typePublicBean = new TypePublicBean();
    @SuppressLint("ValidFragment")
    public ChoiceFragment(Context context, int resId) {
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
        rvTrump.setNestedScrollingEnabled(false);//解决滑动冲突
//        horizontalScrollTV = (AutoHorizontalScrollTextView) v.findViewById(R.id.tv_vertical_advertisement);
//        horizontalScrollTV.setText(strings[0]);

        verticalScrollTV = (AutoVerticalScrollTextView) v.findViewById(R.id.tv_vertical_advertisement);
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

        getHomePageBook();
        Log.d("list111", list1.toString());

    }

    @Override
    protected void BindComponentEvent() {

    }



    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }

    @OnClick({R.id.iv_my, R.id.iv_share, R.id.iv_man,
            R.id.iv_woman, R.id.iv_endbook, R.id.iv_list,
            R.id.iv_search,R.id.tv_vertical_advertisement,
            R.id.tv_recommend,R.id.tv_edit,R.id.tv_trump,R.id.tv_newbook,R.id.tv_must_see,R.id.ll_recommend,R.id.iv_my_img_home})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my:
                break;
            case R.id.iv_search://搜索框
                startActivity(SearchActivity.class,null);
                break;
            case R.id.iv_share://分享
                break;
            case R.id.iv_man://男频
                startActivity(ManChannelActivity.class,null);
                break;
            case R.id.iv_woman://女频
                startActivity(WomanChannelActivity.class,null);
                break;
            case R.id.iv_endbook://完书
                startActivity(EndBookAllActivity.class,null);
                break;
            case R.id.iv_list://榜单
                startActivity(BookListActivity.class,null);
                break;
            case R.id.tv_vertical_advertisement://纵向滚动式广告

                break;
            case R.id.tv_newbook://新书速递更多
                Bundle bundle = new Bundle();
                bundle.putString("bookmore","新书速递");
                startActivity(NewBookMoreActivity.class,bundle);

                break;
            case R.id.tv_must_see://人气推荐更多
                Bundle bundle1 = new Bundle();
                bundle1.putString("bookmore","人气推荐");
                startActivity(NewBookMoreActivity.class,bundle1);

                break;
            case R.id.tv_recommend://主编推荐更多
                Bundle bundle2 = new Bundle();
                bundle2.putString("bookmore","主编推荐");
                startActivity(NewBookMoreActivity.class,bundle2);
                break;
            case R.id.tv_edit://畅读王牌更多
                Bundle bundle3 = new Bundle();
                bundle3.putString("bookmore","畅读王牌");
                startActivity(NewBookMoreActivity.class,bundle3);
                break;
            case R.id.tv_trump://畅读必看更多
                Bundle bundle4 = new Bundle();
                bundle4.putString("bookmore","畅读必看");
                startActivity(NewBookMoreActivity.class,bundle4);
                break;
            case R.id.ll_recommend://人气推荐第一条
                typePublicBean = recommendlist.get(3);//人气推荐
                Bundle bundle5 = new Bundle();
                bundle5.putSerializable("typePublicBean",typePublicBean);
                startActivity(BookInfoActivity.class,bundle5);
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
        banner1.isAutoPlay(true) ;

        //设置轮播图片间隔时间（不设置默认为2000）
        banner1.setDelayTime(3000);
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
                ToastUtil.showShort(_context,"你点击了"+position);
            }
        });
        banner1.start();
    }

    //获取各类型数据
    private void   getHomePageBook(){
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.BOOK_TYPE_HOMEPAGE)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context,"获取失败");
                    }
                    @Override
                    public void onResponse(String response, int id) {
//                        String code = GsonUtil.getJsonFromKey(response,"code");
//                        TypePublicBookBean typePublicBookBean = GsonUtil.getBeanFromJson(response,TypePublicBookBean.class);
                        HomePageBookBean homePageBookBean = GsonUtil.getBeanFromJson(response,HomePageBookBean.class);
                        String code = homePageBookBean.getCode();
                        Log.d("code", code);
                        switch (Integer.parseInt(code)){
                            case 0:
                                String book = GsonUtil.getJsonFromKey(response, "book");
                                list1 = GsonUtil.getListFromJson(book,new TypeToken<List<List<TypePublicBean>>>(){});
                                Log.d("book", book);
                                Log.d("list", list1.toString());
//                                list = GsonUtil.getListFromJson(book,new TypeToken<List<TypePublicBean>>(){});

//                                rvHomePage.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
//                                homePageAdapter = new HomePageAdapter(_context,list);
//                                rvHomePage.setAdapter(homePageAdapter);
                                newBooklist = list1.get(1);//新书速递
                                recommendlist = list1.get(2);//人气推荐
                                editlist = list1.get(3);//主编推荐
                                trumplist = list1.get(4);//畅读王牌
                                mustSeelist = list1.get(0);//畅读必看

                                Log.d("list00", newBooklist.toString());
                                Log.d("list33", recommendlist.toString());
                                Log.d("list44", editlist.toString());
                                Log.d("list55", trumplist.toString());
                                Log.d("list66", mustSeelist.toString());
//
//                              //1
                                rvNewbook.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter1 = new HomePageNewAdapter(_context,newBooklist,1);
                                rvNewbook.setAdapter(homePageNewAdapter1);

                                //2
                                rvRecommend.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter2 = new HomePageNewAdapter(_context,recommendlist,3);
                                rvRecommend.setAdapter(homePageNewAdapter2);
                                Glide.with(_context).load(recommendlist.get(3).getCoverImageUrl()).into(ivBookhome);
                                tvBooknamehome.setText(recommendlist.get(3).getBookName());
                                tvMynamehome.setText(recommendlist.get(3).getAuthorPenname());
                                tvBookintroducehome.setText(recommendlist.get(3).getIntroduction());


                                //3
                                rvEdit.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter3 = new HomePageNewAdapter(_context,editlist,1);
                                rvEdit.setAdapter(homePageNewAdapter3);

                                //4
                                rvTrump.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                homePageNewAdapter4 = new HomePageTrumpAdapter(_context,trumplist,1);
                                rvTrump.setAdapter(homePageNewAdapter4);

                                //5
                                rvMustsee.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter5 = new HomePageNewAdapter(_context,mustSeelist,1);
                                rvMustsee.setAdapter(homePageNewAdapter5);

                                homePageNewAdapter1.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = newBooklist.get(position);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageNewAdapter2.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = recommendlist.get(position);//人气推荐
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageNewAdapter3.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = editlist.get(position); //主编推荐
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });
                                homePageNewAdapter4.setOnItemClickListener(new HomePageTrumpAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = trumplist.get(position);//畅读王牌
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("typePublicBean",typePublicBean);
                                        startActivity(BookInfoActivity.class,bundle);
                                    }
                                });

                                homePageNewAdapter5.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = mustSeelist.get(position);//畅读必看
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

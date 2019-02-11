package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.ManBean;
import com.huancheng.reader.ui.activity.sub.BookClassIficationActivity;
import com.huancheng.reader.ui.adapter.sub.ManAdapter;
import com.huancheng.reader.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/12/18.
 */

@SuppressLint("ValidFragment")
public class ManFragment extends BaseFragment {

    @BindView(R.id.tv_num_number)
    TextView tvNumNumber;
    @BindView(R.id.tv_add_number)
    TextView tvAddNumber;
    @BindView(R.id.iv_man_urban)
    ImageView ivManUrban;
    @BindView(R.id.iv_man_fantasy)
    ImageView ivManFantasy;
    @BindView(R.id.iv_man_knigh_errant)
    ImageView ivManKnighErrant;
    @BindView(R.id.iv_man_history)
    ImageView ivManHistory;
    @BindView(R.id.iv_man_novel)
    ImageView ivManNovel;
    @BindView(R.id.iv_man_suspense)
    ImageView ivManSuspense;
    @BindView(R.id.iv_man_game)
    ImageView ivManGame;

    ManBean manBean;
    private ManAdapter manAdapter;
    List<ManBean> list = new ArrayList<ManBean>();
    @SuppressLint("ValidFragment")
    public ManFragment(Context context, int resId) {
        super(context, resId);
        _context = context;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        ButterKnife.bind(this,v);
    }

    @Override
    protected void initData() {
//        list.add(new ManBean(R.mipmap.book_book,"现代都市","41555条"));
//        list.add(new ManBean(R.mipmap.book_book,"玄幻奇幻","41555条"));
//        list.add(new ManBean(R.mipmap.book_book,"武侠仙侠","41555条"));
//        list.add(new ManBean(R.mipmap.book_book,"历史军事","41555条"));
//        list.add(new ManBean(R.mipmap.book_book,"科幻小说","41555条"));
//        list.add(new ManBean(R.mipmap.book_book,"灵异悬疑","41555条"));
//        list.add(new ManBean(R.mipmap.book_book,"游戏竞技","41555条"));
//        manAdapter = new ManAdapter(list);
//        rvMan.setLayoutManager(new LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false));
//        rvMan.setAdapter(manAdapter);
    }

    @Override
    protected void BindComponentEvent() {

    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {


    }
    @OnClick({R.id.iv_man_urban, R.id.iv_man_fantasy, R.id.iv_man_knigh_errant, R.id.iv_man_history, R.id.iv_man_novel, R.id.iv_man_suspense, R.id.iv_man_game})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_man_urban:
                Bundle bundle = new Bundle();
                bundle.putString("booktype","都市小说");
                startActivity(BookClassIficationActivity.class,bundle);
                break;
            case R.id.iv_man_fantasy:
                Bundle bundle1 = new Bundle();
                bundle1.putString("booktype","玄幻奇幻");
                startActivity(BookClassIficationActivity.class,bundle1);
                break;
            case R.id.iv_man_knigh_errant:
                Bundle bundle2 = new Bundle();
                bundle2.putString("booktype","仙侠武侠");
                startActivity(BookClassIficationActivity.class,bundle2);
                break;
            case R.id.iv_man_history:
                Bundle bundle3 = new Bundle();
                bundle3.putString("booktype","历史军事");
                startActivity(BookClassIficationActivity.class,bundle3);
                break;
            case R.id.iv_man_novel:
                Bundle bundle4 = new Bundle();
                bundle4.putString("booktype","科幻末世");
                startActivity(BookClassIficationActivity.class,bundle4);
                break;
            case R.id.iv_man_suspense:
                Bundle bundle5 = new Bundle();
                bundle5.putString("booktype","悬疑小说");
                startActivity(BookClassIficationActivity.class,bundle5);
                break;
            case R.id.iv_man_game:
                Bundle bundle6 = new Bundle();
                bundle6.putString("booktype","游戏竞技");
                startActivity(BookClassIficationActivity.class,bundle6);
                break;
        }
    }

}


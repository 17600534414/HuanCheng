package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.ManPublicBean;
import com.huancheng.reader.ui.adapter.sub.ManPublicAdapter;
import com.huancheng.reader.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/12/21.
 */

@SuppressLint("ValidFragment")
public class EndWomanFragment extends BaseFragment {
    @BindView(R.id.rv_man_public)
    RecyclerView rvManPublic;
    @BindView(R.id.tv_common_book)
    TextView tvCommonBook;
    @BindView(R.id.tv_add_book)
    TextView tvAddBook;

    private List<ManPublicBean> list = new ArrayList();
    private ManPublicBean manPublicBean;
    private ManPublicAdapter manPublicAdapter = new ManPublicAdapter();
    @SuppressLint("ValidFragment")
    public EndWomanFragment(Context context, int resId) {
        super(context, resId);
        _context = context;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        ButterKnife.bind(this,v);

    }

    @Override
    protected void initData() {

        list.add(new ManPublicBean(R.mipmap.book_book,"女生1","女生介绍1",R.mipmap.login_code2,"女生个人昵称1","类型1"));
        list.add(new ManPublicBean(R.mipmap.book_book,"女生2","女生介绍2",R.mipmap.login_code2,"女生个人昵称2","类型2"));
        list.add(new ManPublicBean(R.mipmap.book_book,"女生3","女生介绍3",R.mipmap.login_code2,"女生个人昵称3","类型3"));

//        rvManPublic.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
//        manPublicAdapter = new ManPublicAdapter(_context,list,1);
//        rvManPublic.setAdapter(manPublicAdapter);
    }

    @Override
    protected void BindComponentEvent() {

    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
}

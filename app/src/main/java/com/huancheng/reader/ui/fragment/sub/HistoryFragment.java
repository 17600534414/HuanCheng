package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.ManPublicBean;
import com.huancheng.reader.bean.TypePublicBean;
import com.huancheng.reader.bean.TypePublicBookBean;
import com.huancheng.reader.ui.adapter.sub.ManPublicAdapter;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by admin on 2018/12/20.
 */

@SuppressLint("ValidFragment")
public class HistoryFragment extends BaseFragment {@BindView(R.id.rv_man_public)
RecyclerView rvManPublic;
    @BindView(R.id.tv_common_book)
    TextView tvCommonBook;
    @BindView(R.id.tv_add_book)
    TextView tvAddBook;

    private List<ManPublicBean> list1 = new ArrayList();
    private List<TypePublicBean> list = new ArrayList();
    private String type = "";
    private int code = 0;
    private ManPublicBean manPublicBean;
    private ManPublicAdapter manPublicAdapter = new ManPublicAdapter();

    @SuppressLint("ValidFragment")
    public HistoryFragment(Context context, int resId, String type, int code) {
        super(context, resId);
        _context = context;
        this.type = type;
        this.code = code;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        ButterKnife.bind(this,v);
    }

    @Override
    protected void initData() {
//        list.add(new ManPublicBean(R.mipmap.book_book,"历史军事1","历史军事介绍1",R.mipmap.login_code2,"历史军事个人昵称1"));
//        list.add(new ManPublicBean(R.mipmap.book_book,"历史军事2","历史军事介绍2",R.mipmap.login_code2,"历史军事个人昵称2"));
//        list.add(new ManPublicBean(R.mipmap.book_book,"历史军事3","历史军事介绍3",R.mipmap.login_code2,"历史军事个人昵称3"));

//        rvManPublic.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
//        manPublicAdapter = new ManPublicAdapter(_context,list);
//        rvManPublic.setAdapter(manPublicAdapter);
        getBookData();
    }

    @Override
    protected void BindComponentEvent() {

    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }
    private void getBookData(){
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.BOOK_TYPE)
                .addParams("channelName", type)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(_context,"获取失败");
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        TypePublicBookBean typePublicBookBean = GsonUtil.getBeanFromJson(response,TypePublicBookBean.class);


                        switch (Integer.parseInt(typePublicBookBean.getCode())){
                            case 0:
                                String book = GsonUtil.getJsonFromKey(response,"book");
                                Log.d("list", book);
                                list = GsonUtil.getListFromJson(book,new TypeToken<List<TypePublicBean>>(){});

                                rvManPublic.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                manPublicAdapter = new ManPublicAdapter(_context,list);
                                rvManPublic.setAdapter(manPublicAdapter);
                                manPublicAdapter.notifyDataSetChanged();
                                break;
                            case 500:
                                break;
                        }
                    }
                });
    }
}

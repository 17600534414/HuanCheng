package com.huancheng.reader.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class NewBookMoreActivity extends BaseBussActivity {

    @BindView(R.id.rv_new_book_more)
    RecyclerView rvNewBookMore;

    private HomePageNewAdapter homePageNewAdapter;
    List<TypePublicBean> list1 = new ArrayList<TypePublicBean>();
    TypePublicBean typePublicBean = new TypePublicBean();
    private String type = "";

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = NewBookMoreActivity.this;
        setContentView(R.layout.activity_new_book_more);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        getNewBookMore();
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        type = intent.getStringExtra("bookmore");

    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }

    //获取各类型数据
    private void   getNewBookMore(){
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.BOOK_TYPE_HOMEPAGE)
                .addParams("****",type)
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
                        EndBookAllBean endBookAllBean = GsonUtil.getBeanFromJson(response, EndBookAllBean.class);
                        String code = endBookAllBean.getCode();
                        Log.d("code", code);
                        switch (Integer.parseInt(code)){
                            case 0:
                                String book = GsonUtil.getJsonFromKey(response, "book");
                                list1 = GsonUtil.getListFromJson(book,new TypeToken<List<TypePublicBean>>(){});
                                rvNewBookMore.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
                                homePageNewAdapter = new HomePageNewAdapter(_context,list1,1);
                                rvNewBookMore.setAdapter(homePageNewAdapter);

                                homePageNewAdapter.setOnItemClickListener(new HomePageNewAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = list1.get(position);//人气推荐
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

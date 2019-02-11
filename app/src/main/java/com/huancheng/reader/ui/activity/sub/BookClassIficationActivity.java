package com.huancheng.reader.ui.activity.sub;

import android.content.Intent;
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
import com.huancheng.reader.R;
import com.huancheng.reader.bean.TypePublicBean;
import com.huancheng.reader.bean.TypePublicBookBean;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.ui.adapter.sub.BookTypeListAdapter;
import com.huancheng.reader.ui.adapter.sub.HomePageNewAdapter;
import com.huancheng.reader.ui.adapter.sub.ManPublicAdapter;
import com.huancheng.reader.ui.adapter.sub.TypePublicAdapter;
import com.huancheng.reader.util.GsonUtil;
import com.huancheng.reader.util.HttpUtil;
import com.huancheng.reader.util.ToastUtil;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class BookClassIficationActivity extends BaseBussActivity {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.rv_type_public)
    RecyclerView rvTypePublic;
    @BindView(R.id.rv_word_number)
    RecyclerView rvWordNumber;
    @BindView(R.id.rv_serialization)
    RecyclerView rvSerialization;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.rv_book_list)
    RecyclerView rvBookList;

    private String type = "";
    private List<String> listType = new ArrayList<String>();
    private List<String> listWordNumber = new ArrayList<String>();
    private List<String> listSerialization = new ArrayList<String>();
    private List<String> listList = new ArrayList<String>();
    private List listBookList = new ArrayList();
    private TypePublicAdapter typePublicAdapter1,typePublicAdapter2,typePublicAdapter3,typePublicAdapter4;
    private String listTypeContent = "",listWordNumberContent = "",listSerializationContent = "",listListContent = "";
    private List<TypePublicBean> list = new ArrayList<>();
    private BookTypeListAdapter bookTypeListAdapter;
    TypePublicBean typePublicBean = new TypePublicBean();

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = BookClassIficationActivity.this;
        setContentView(R.layout.activity_book_class_ification);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        String type1 = "全部";
        String type2 = "都市言情";
        String type3 = "都市言情";
        String type4 = "都市言情";
        String type5 = "都市言情";
        String type6 = "都市言情";
        listType.add(type1);
        listType.add(type2);
        listType.add(type3);
        listType.add(type4);
        listType.add(type5);
        String wordNumber1 = "全部";
        String wordNumber2 = "30万字以下";
        String wordNumber3 = "30-100万字";
        String wordNumber4 = "100万字以上";
        listWordNumber.add(wordNumber1);
        listWordNumber.add(wordNumber2);
        listWordNumber.add(wordNumber3);
        listWordNumber.add(wordNumber4);
        String serialization1 = "全部";
        String serialization2 = "完结";
        String serialization3 = "连载";
        listSerialization.add(serialization1);
        listSerialization.add(serialization2);
        listSerialization.add(serialization3);
        String list1 = "人气最高";
        String list2 = "评论最多";
        String list3 = "最新上架";
        String list4 = "最新更新";
        listList.add(list1);
        listList.add(list2);
        listList.add(list3);
        listList.add(list4);

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        type = intent.getStringExtra("booktype");
        tvBack.setText(type);
        tvBack.setTextColor(Color.WHITE);

        getBookData(type);

        rvTypePublic.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
        typePublicAdapter1 = new TypePublicAdapter(_context,listType,1);
        rvTypePublic.setAdapter(typePublicAdapter1);
        typePublicAdapter1.setOnItemClickListener(new TypePublicAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                listTypeContent = listType.get(position);
                ToastUtil.showShort(_context,listTypeContent);
                typePublicAdapter1.setThisPosition(position);
                //嫑忘记刷新适配器
                typePublicAdapter1.notifyDataSetChanged();
            }
        });

        rvWordNumber.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
        typePublicAdapter2 = new TypePublicAdapter(_context,listWordNumber,1);
        rvWordNumber.setAdapter(typePublicAdapter2);
        typePublicAdapter2.setOnItemClickListener(new TypePublicAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                listWordNumberContent = listWordNumber.get(position);
                ToastUtil.showShort(_context,listWordNumberContent);
                //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                typePublicAdapter2.setThisPosition(position);
                //嫑忘记刷新适配器
                typePublicAdapter2.notifyDataSetChanged();
            }
        });

        rvSerialization.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
        typePublicAdapter3 = new TypePublicAdapter(_context,listSerialization,1);
        rvSerialization.setAdapter(typePublicAdapter3);
        typePublicAdapter3.setOnItemClickListener(new TypePublicAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                listSerializationContent = listSerialization.get(position);
                ToastUtil.showShort(_context,listSerializationContent);
                typePublicAdapter3.setThisPosition(position);
                //嫑忘记刷新适配器
                typePublicAdapter3.notifyDataSetChanged();
            }
        });

        rvList.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.HORIZONTAL,false));
        typePublicAdapter4 = new TypePublicAdapter(_context,listList,1);
        rvList.setAdapter(typePublicAdapter4);
        typePublicAdapter4.setOnItemClickListener(new TypePublicAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                listListContent = listList.get(position);
                listList.get(position);
                ToastUtil.showShort(_context,listListContent);
                typePublicAdapter4.setThisPosition(position);
                //嫑忘记刷新适配器
                typePublicAdapter4.notifyDataSetChanged();
            }
        });
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

    private void getBookData(String type){
        OkHttpUtils
                .post()
                .url(HttpUtil.SOCKET_HOST+HttpUtil.BOOK_TYPE)
                .addParams("channelName", type)
                .addParams("classification", "1")
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
                                if (list != null){
                                    rvBookList.setLayoutManager (new GridLayoutManager(_context,1,GridLayoutManager.VERTICAL,false));
                                    bookTypeListAdapter = new BookTypeListAdapter(_context,list);
                                    rvBookList.setAdapter(bookTypeListAdapter);
                                }
                                bookTypeListAdapter.setOnItemClickListener(new BookTypeListAdapter.OnItemClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        //获取到书籍的数据携带typepublicbean跳到书籍详情页
                                        typePublicBean = list.get(position);
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

package com.huancheng.reader.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.TypePublicBean;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.wegiht.ExpandableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookInfoActivity extends BaseBussActivity {

    @BindView(R.id.iv_my)
    ImageView ivMy;
    @BindView(R.id.iv_book)
    ImageView ivBook;
    @BindView(R.id.iv_sign)
    ImageView ivSign;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_bookimg_bookinfo)
    ImageView ivBookimgBookinfo;
    @BindView(R.id.tv_bookname_bookinfo)
    TextView tvBooknameBookinfo;
    @BindView(R.id.tv_booktype_bookinfo)
    TextView tvBooktypeBookinfo;
    @BindView(R.id.tv_booknumber_bookinfo)
    TextView tvBooknumberBookinfo;
    @BindView(R.id.tv_vip_bookinfo)
    TextView tvVipBookinfo;
    @BindView(R.id.tv_collection_bookinfo)
    TextView tvCollectionBookinfo;
    @BindView(R.id.tv_redunmber_bookinfo)
    TextView tvRedunmberBookinfo;
    @BindView(R.id.tv_score_info)
    TextView tvScoreInfo;
    @BindView(R.id.tv_introduce_bookinfo)
    ExpandableTextView tvIntroduceBookinfo;
    @BindView(R.id.iv_download_bookinfo)
    ImageView ivDownloadBookinfo;
    @BindView(R.id.iv_startred_info)
    ImageView ivStartredInfo;
    @BindView(R.id.iv_add_info)
    ImageView ivAddInfo;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = BookInfoActivity.this;
        setContentView(R.layout.activity_bookinfo);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        // 实例化一个Bundle
        Bundle bundle = intent.getExtras();
        //获取里面的Persion里面的数据
        TypePublicBean typePublicBean = (TypePublicBean) bundle.getSerializable("typePublicBean");
        Glide.with(_context).load(typePublicBean.getCoverImageUrl()).into(ivBookimgBookinfo);
        tvBooknameBookinfo.setText(typePublicBean.getBookName());
        tvBooktypeBookinfo.setText(typePublicBean.getCategoryName());
        tvBooknumberBookinfo.setText(typePublicBean.getWordCount());
        tvIntroduceBookinfo.setText(typePublicBean.getIntroduction());

    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }

    @OnClick({R.id.iv_my, R.id.iv_book, R.id.iv_sign, R.id.iv_search, R.id.iv_bookimg_bookinfo, R.id.tv_vip_bookinfo, R.id.tv_collection_bookinfo, R.id.tv_redunmber_bookinfo, R.id.tv_score_info, R.id.tv_introduce_bookinfo, R.id.iv_download_bookinfo, R.id.iv_startred_info, R.id.iv_add_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my:
                break;
            case R.id.iv_book:
                break;
            case R.id.iv_sign:
                break;
            case R.id.iv_search:
                break;
            case R.id.iv_bookimg_bookinfo:
                break;
            case R.id.tv_vip_bookinfo:
                break;
            case R.id.tv_collection_bookinfo:
                break;
            case R.id.tv_redunmber_bookinfo:
                break;
            case R.id.tv_score_info:
                break;
            case R.id.tv_introduce_bookinfo:
                break;
            case R.id.iv_download_bookinfo:
                break;
            case R.id.iv_startred_info:
                break;
            case R.id.iv_add_info:
                break;
        }
    }
}

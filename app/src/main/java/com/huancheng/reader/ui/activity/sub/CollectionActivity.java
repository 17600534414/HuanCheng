package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionActivity extends BaseBussActivity {


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_right_title_bar)
    TextView tvRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = CollectionActivity.this;
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("收藏");
        tvRightTitleBar.setText("删除");

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }


    @OnClick({R.id.iv_left_title_bar, R.id.tv_right_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.tv_right_title_bar:
                startActivity(CollectionDelActivity.class, null);
                break;
        }
    }
}

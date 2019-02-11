package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionDelActivity extends BaseBussActivity {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_right_title_bar)
    TextView tvRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.btn_del)
    Button btnDel;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = CollectionDelActivity.this;
        setContentView(R.layout.activity_collection_del);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvRightTitleBar.setText("全选");
        tvBack.setText("收藏");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_left_title_bar, R.id.tv_right_title_bar, R.id.btn_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.tv_right_title_bar:
                break;
            case R.id.btn_del:
                break;
        }
    }
}

package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseBussActivity {


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = AboutActivity.this;
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("关于");
        ivRightTitleBar.setVisibility(View.GONE);
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

    @OnClick({R.id.iv_left_title_bar, R.id.rl_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.rl_title_bar:
                break;
        }
    }
}

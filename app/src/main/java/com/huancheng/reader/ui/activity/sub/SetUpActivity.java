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

public class SetUpActivity extends BaseBussActivity {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @BindView(R.id.rl_evaluate)
    RelativeLayout rlEvaluate;
    @BindView(R.id.rl_opinion)
    RelativeLayout rlOpinion;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.rl_help)
    RelativeLayout rlHelp;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = SetUpActivity.this;
        setContentView(R.layout.activity_set_up);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("设置");
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


    @OnClick({R.id.iv_left_title_bar, R.id.rl_title_bar, R.id.rl_update, R.id.rl_evaluate, R.id.rl_opinion, R.id.rl_about,R.id.rl_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.rl_update:
                break;
            case R.id.rl_evaluate:
                break;
            case R.id.rl_opinion://意见反馈
                startActivity(OpinionActivity.class,null);
                break;
            case R.id.rl_about://关于
                startActivity(AboutActivity.class,null);
                break;
            case R.id.rl_help://帮助
                startActivity(HelpActivity.class,null);
                break;
        }
    }
}

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

public class HelpActivity extends BaseBussActivity {


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.rl_what_book)
    RelativeLayout rlWhatBook;
    @BindView(R.id.rl_get_book)
    RelativeLayout rlGetBook;
    @BindView(R.id.rl_what_flower)
    RelativeLayout rlWhatFlower;
    @BindView(R.id.rl_get_flower)
    RelativeLayout rlGetFlower;
    @BindView(R.id.rl_limited_book)
    RelativeLayout rlLimitedBook;
    @BindView(R.id.rl_review_book)
    RelativeLayout rlReviewBook;
    @BindView(R.id.rl_reward_sign)
    RelativeLayout rlRewardSign;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = HelpActivity.this;
        setContentView(R.layout.activity_help);
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


    @OnClick({R.id.iv_left_title_bar, R.id.rl_what_book, R.id.rl_get_book, R.id.rl_what_flower, R.id.rl_get_flower, R.id.rl_limited_book, R.id.rl_review_book, R.id.rl_reward_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.rl_what_book:
                break;
            case R.id.rl_get_book:
                break;
            case R.id.rl_what_flower:

                break;
            case R.id.rl_get_flower:
                break;
            case R.id.rl_limited_book:
                break;
            case R.id.rl_review_book:
                break;
            case R.id.rl_reward_sign:
                break;
        }
    }
}

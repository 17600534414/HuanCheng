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

public class MyFriendActivity extends BaseBussActivity {


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_ranking)
    TextView tvRanking;
    @BindView(R.id.tv_read_time)
    TextView tvReadTime;
    @BindView(R.id.rv_friend)
    RecyclerView rvFriend;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = MyFriendActivity.this;
        setContentView(R.layout.activity_my_friend);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        tvBack.setText("我的好友");
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

    @OnClick({R.id.iv_left_title_bar, R.id.rv_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.rv_friend:
                break;
        }
    }
}

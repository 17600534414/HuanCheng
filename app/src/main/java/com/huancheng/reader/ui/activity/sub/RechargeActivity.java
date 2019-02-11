package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeActivity extends BaseBussActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_pay)
    ImageView ivPay;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = RechargeActivity.this;
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }

    @OnClick({R.id.iv_back, R.id.iv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_pay:
                break;
        }
    }
}

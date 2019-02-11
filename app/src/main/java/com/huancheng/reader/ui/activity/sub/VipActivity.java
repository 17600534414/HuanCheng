package com.huancheng.reader.ui.activity.sub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;

import butterknife.ButterKnife;

public class VipActivity extends BaseBussActivity {

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = VipActivity.this;
        setContentView(R.layout.activity_vip);
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
}

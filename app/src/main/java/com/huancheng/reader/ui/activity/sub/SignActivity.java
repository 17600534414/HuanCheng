package com.huancheng.reader.ui.activity.sub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.util.DateUtile;
import com.huancheng.reader.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignActivity extends BaseBussActivity {


    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;

    private String way;//星期几

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = SignActivity.this;
        setContentView(R.layout.activity_sign);
        ButterKnife.bind( this ) ;
    }

    @Override
    protected void initView() {
        super.initView();
        ivRightTitleBar.setVisibility(View.GONE);
        tvBack.setText("签到");

    }

    @Override
    protected void initData() {
        super.initData();
        way = DateUtile.GetWay();
        ToastUtil.showShort(_context,way);
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }


    @OnClick({R.id.iv_left_title_bar, R.id.tv_back, R.id.iv_right_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
        }
    }
}

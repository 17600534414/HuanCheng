package com.huancheng.reader.ui.activity.sub;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.util.DisplayUtils;
import com.huancheng.reader.util.DrawableUtils;
import com.huancheng.reader.wegiht.SearchView;
import com.huancheng.reader.wegiht.YhFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseBussActivity {

    @BindView(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.flowlayout)
    YhFlowLayout flowlayout;
    private YhFlowLayout flowLayout;
    private List<String> mDatas;

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        _context = SearchActivity.this;
        mDatas = new ArrayList<String>();
        mDatas.add("android");
        mDatas.add("php");
        mDatas.add("c#");
        mDatas.add("JavaScript");
        mDatas.add("ios");
        mDatas.add("万万没想到");
        mDatas.add("疯狂动物城");
        mDatas.add("功夫熊猫3");
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        flowLayout = (YhFlowLayout) findViewById(R.id.flowlayout);
        flowLayout.setSpace(DisplayUtils.dp2Px(this, 10), DisplayUtils.dp2Px(this, 10));
        flowLayout.setPadding(DisplayUtils.dp2Px(this, 10), DisplayUtils.dp2Px(this, 10),
                DisplayUtils.dp2Px(this, 5), DisplayUtils.dp2Px(this, 5));

        displayUI();
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

    private void displayUI() {
        for (int i = 0; i < mDatas.size(); i++) {
            final String data = mDatas.get(i);
            final TextView tv = new TextView(this);
            tv.setText(data);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tv.setGravity(Gravity.CENTER);
            int paddingy = DisplayUtils.dp2Px(this, 7);
            int paddingx = DisplayUtils.dp2Px(this, 6);
            tv.setPadding(paddingx, paddingy, paddingx, paddingy);
            tv.setClickable(false);

            int shape = GradientDrawable.RECTANGLE;
            int radius = DisplayUtils.dp2Px(this, 4);
            int strokeWeight = DisplayUtils.dp2Px(this, 1);
            int stokeColor = getResources().getColor(R.color.colorPrimary);
            int stokeColor2 = getResources().getColor(R.color.green);

            GradientDrawable normalBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor, getResources().getColor(R.color.button_yellow));
            GradientDrawable pressedBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor2, getResources().getColor(R.color.green));
            StateListDrawable selector = DrawableUtils.getSelector(normalBg, pressedBg);
            tv.setBackgroundDrawable(selector);
            ColorStateList colorStateList = DrawableUtils.getColorSelector(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.white));
            tv.setTextColor(colorStateList);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    search.setText(tv.getText());

                }
            });
            flowLayout.addView(tv);
        }
    }

    @OnClick({R.id.iv_left_title_bar, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_title_bar:
                onBackPressed();
                break;
            case R.id.tv_search:
                break;
        }
    }
}

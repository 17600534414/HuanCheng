package com.huancheng.reader.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.ui.activity.sub.BookClassIficationActivity;
import com.huancheng.reader.ui.fragment.base.BaseFragment;
import com.huancheng.reader.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/12/18.
 */

@SuppressLint("ValidFragment")
public class WomanFragment extends BaseFragment {

    @BindView(R.id.tv_num_number)
    TextView tvNumNumber;
    @BindView(R.id.tv_add_number)
    TextView tvAddNumber;
    @BindView(R.id.iv_woman_modernromance)
    ImageView ivWomanModernromance;
    @BindView(R.id.iv_woman_ancientromance)
    ImageView ivWomanAncientromance;
    @BindView(R.id.iv_woman_fantasyromance)
    ImageView ivWomanFantasyromance;
    @BindView(R.id.iv_woman_youth)
    ImageView ivWomanYouth;
    @BindView(R.id.iv_woman_girlsuspense)
    ImageView ivWomanGirlsuspense;
    @BindView(R.id.iv_woman_purelove)
    ImageView ivWomanPurelove;
    @SuppressLint("ValidFragment")
    public WomanFragment(Context context, int resId) {
        super(context, resId);
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {

        ButterKnife.bind(this,v);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void BindComponentEvent() {

    }

    @Override
    protected void doActivityResult(int requestCode, Intent intent) {

    }

    @OnClick({R.id.iv_woman_modernromance, R.id.iv_woman_ancientromance, R.id.iv_woman_fantasyromance, R.id.iv_woman_youth, R.id.iv_woman_girlsuspense, R.id.iv_woman_purelove})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_woman_modernromance:
                Bundle bundle = new Bundle();
                bundle.putString("booktype","都市言情");
                startActivity(BookClassIficationActivity.class,bundle);
                break;
            case R.id.iv_woman_ancientromance:
                Bundle bundle1 = new Bundle();
                bundle1.putString("booktype","古装言情");
                startActivity(BookClassIficationActivity.class,bundle1);
                break;
            case R.id.iv_woman_fantasyromance:
                Bundle bundle2 = new Bundle();
                bundle2.putString("booktype","幻想言情");
                startActivity(BookClassIficationActivity.class,bundle2);
                break;
            case R.id.iv_woman_youth:
                Bundle bundle3 = new Bundle();
                bundle3.putString("booktype","浪漫青春");
                startActivity(BookClassIficationActivity.class,bundle3);
                break;
            case R.id.iv_woman_girlsuspense:
                Bundle bundle4 = new Bundle();
                bundle4.putString("booktype","悬疑小说");
                startActivity(BookClassIficationActivity.class,bundle4);
                break;
            case R.id.iv_woman_purelove:
                Bundle bundle5 = new Bundle();
                bundle5.putString("booktype","情感小说");
                startActivity(BookClassIficationActivity.class,bundle5);
                break;
        }
    }
}

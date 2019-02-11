package com.huancheng.reader.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.BookBean;
import com.huancheng.reader.ui.activity.base.BaseBussActivity;
import com.huancheng.reader.ui.adapter.sub.BookShelfAdapter;
import com.huancheng.reader.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/12/17.
 */

public class FinishingBookActivity extends BaseBussActivity {
    @BindView(R.id.tv_left_title_bar)
    TextView tvLeftTitleBar;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_right_title_bar)
    TextView tvRightTitleBar;
    @BindView(R.id.rv_finishbook)
    RecyclerView rvFinishbook;

    private List<BookBean> list1 = new ArrayList();

    @Override
    protected void setCustomLayout(Bundle savedInstanceState) {
        super.setCustomLayout(savedInstanceState);
        _context = FinishingBookActivity.this;
        setContentView(R.layout.activity_finishbook);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();


    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        list1  = (List<BookBean>) intent.getSerializableExtra("list");
        rvFinishbook.setLayoutManager (new GridLayoutManager(_context,3,GridLayoutManager.VERTICAL,false));
        BookShelfAdapter bookShelfAdapter = new BookShelfAdapter(_context,list1,2);
        rvFinishbook.setAdapter(bookShelfAdapter);
        bookShelfAdapter.setOnItemClickListener(new BookShelfAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //这里的RecyclerView的点击事件
            }
        });
    }

    @Override
    protected void BindComponentEvent() {
        super.BindComponentEvent();
    }


    @OnClick({R.id.tv_left_title_bar, R.id.tv_right_title_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left_title_bar:
                break;
            case R.id.tv_right_title_bar:
                break;
        }
    }
}

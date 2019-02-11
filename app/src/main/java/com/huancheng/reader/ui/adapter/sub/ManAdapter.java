package com.huancheng.reader.ui.adapter.sub;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.ManBean;

import java.util.List;

/**
 * Created by admin on 2018/12/19.
 */

public class ManAdapter extends BaseMultiItemQuickAdapter<ManBean,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ManAdapter(List<ManBean> data) {
        super(data);
//        addItemType(0, R.layout.alayout);
//        addItemType(1, R.layout.blayout);

    }

    @Override
    protected void convert(BaseViewHolder helper, ManBean item) {

    }
}

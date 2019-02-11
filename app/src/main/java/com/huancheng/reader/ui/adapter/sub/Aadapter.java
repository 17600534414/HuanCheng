package com.huancheng.reader.ui.adapter.sub;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huancheng.reader.bean.ABean;

import java.util.List;

/**
 * Created by admin on 2018/12/18.
 */

public class Aadapter extends BaseMultiItemQuickAdapter<ABean,BaseViewHolder>{

//现在这个Bean就不报错了
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    //在构造方法里面写你要展示的多种布局  登哥你往下画一下  我划不下去
    public Aadapter(List<ABean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ABean item) {

    }
}

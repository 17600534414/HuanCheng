package com.huancheng.reader.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.TypePublicBean;

import java.util.List;

/**
 * Created by admin on 2019/1/10.
 */

public class HomePageAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<List<TypePublicBean>> list;
    public HomePageAdapter(Context context, List<List<TypePublicBean>> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        list.get(0)
//                View view = LayoutInflater.from(context).inflate(R.layout.adapter_man_public_item,parent,false);
//                return new HomePageAdapter.ViewHolder(view);
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

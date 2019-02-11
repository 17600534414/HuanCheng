package com.huancheng.reader.ui.adapter.sub;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.ChannelBean;
import com.huancheng.reader.bean.ChannelHorizontalBean;
import com.huancheng.reader.bean.ChannelVerticalBean;

import java.util.List;

/**
 * Created by admin on 2019/1/15.
 */

public class ChannelAdapter extends RecyclerView.Adapter {
    public final static int VERTICAL = 1001;//标题的viewType
    public final static int HORIZONTAL = 1002;//横向列表的viewType

    private List<ChannelBean> mlist;//adapter的数据源
    private Context context;
    private LayoutInflater inflater;


    public ChannelAdapter(List<ChannelBean> mlist) {
        this.mlist = mlist;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null)
            context = parent.getContext();
        if (inflater == null)
            inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case VERTICAL:
                view = inflater.inflate(R.layout.adaoter_channel_vertical, parent, false);
                return new VerticalHolder(view);
            case HORIZONTAL:
                view = inflater.inflate(R.layout.adapter_channel_horizonta, parent, false);
                return new HorizontalHolder(view);
        }
        return null;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VerticalHolder) {
            ChannelVerticalBean channelVerticalBean1 = (ChannelVerticalBean) mlist.get(position);
            ((VerticalHolder) holder).rv_channel_vertical.setLayoutManager (new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false));
            ((VerticalHolder) holder).rv_channel_vertical.setAdapter(new HomePageTrumpAdapter(context, channelVerticalBean1.getManchannelVertical(),1));
        }

        if (holder instanceof HorizontalHolder) {
            ChannelHorizontalBean channelHorizontalBean = (ChannelHorizontalBean) mlist.get(position);
            ((HorizontalHolder) holder).rv_channel_horizonta.setLayoutManager (new GridLayoutManager(context,3,GridLayoutManager.HORIZONTAL,false));
            ((HorizontalHolder) holder).rv_channel_horizonta.setAdapter(new HomePageNewAdapter(context, channelHorizontalBean.getManchannelHorizontal(),1));
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mlist.size() > 0) {
            return mlist.get(position).getViewType();
        }
        return super.getItemViewType(position);
    }

    private class VerticalHolder extends RecyclerView.ViewHolder {

        RecyclerView rv_channel_vertical;

        public VerticalHolder(View itemView) {
            super(itemView);

            rv_channel_vertical = itemView.findViewById(R.id.rv_channel_vertical);
        }
    }
    private class HorizontalHolder extends RecyclerView.ViewHolder {

        RecyclerView rv_channel_horizonta;

        public HorizontalHolder(View itemView) {
            super(itemView);
            rv_channel_horizonta = itemView.findViewById(R.id.rv_channel_horizonta);
        }
    }

}

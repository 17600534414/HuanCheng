package com.huancheng.reader.ui.adapter.sub;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huancheng.reader.R;
import com.huancheng.reader.bean.ManPublicBean;
import com.huancheng.reader.bean.TypePublicBean;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by admin on 2019/1/14.
 */

public class TypePublicAdapter extends RecyclerView.Adapter<TypePublicAdapter.ViewHolder> {
    private List<String> list;
    private Context context;
    private int type = 0;
    public TypePublicAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }
    public TypePublicAdapter(Context context, List<String> list, int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }
    @Override
    public TypePublicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_type_public_item,parent,false);
        return new TypePublicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_book_type.setText(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
                if (holder.getLayoutPosition() != 0){
                    if (position == holder.getLayoutPosition()){
                        holder.tv_book_type.setBackgroundResource(R.drawable.bg_book_type_item);
                    }
                }

            }
        });
        final ViewHolder recViewHolderLeft = (ViewHolder) holder;

        if (position == getthisPosition()) {
//            recViewHolderLeft.tv_book_type.setBackgroundColor(Color.BLUE);
            recViewHolderLeft.ll_book_type.setBackgroundResource(R.drawable.bg_book_type_public);
        } else {
//            recViewHolderLeft.tv_book_type.setBackgroundColor(Color.WHITE);
            recViewHolderLeft.ll_book_type.setBackgroundResource(R.drawable.bg_book_type_public_1);
        }
//        ViewHolder viewViewHolder = (ViewHolder) holder;
//        if (viewViewHolder != null) {
//            viewViewHolder.tv_book_type.setText(list.get(position));
//
//            ///////////////////////////////////點擊變色/////////////////////////////////////////////
//            if (position == getthisPosition()) {
//                //選中的顔色就設成了  黃色
//                viewViewHolder.tv_book_type.setBackgroundResource(R.drawable.btn_bg);
//            }
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_book_type;
        private LinearLayout ll_book_type;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_book_type = itemView.findViewById(R.id.tv_book_type);
            ll_book_type = itemView.findViewById(R.id.ll_book_type);
        }
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private TypePublicAdapter.OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(TypePublicAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemLongClickListener {
        void onClick(int position);
    }

    private TypePublicAdapter.OnItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(TypePublicAdapter.OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
    private int thisPosition;
    private OnItemClickListener onRecyclerViewItemClickListener;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }
//
    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }
//
//    public void setOnRecyclerViewItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onRecyclerViewItemClickListener = onItemClickListener;
//    }

}

package com.huancheng.reader.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.TypePublicBean;

import java.util.List;

/**
 * Created by admin on 2019/1/11.
 */

public class HomePageTrumpAdapter extends RecyclerView.Adapter<HomePageTrumpAdapter.ViewHolder> {

    private List<TypePublicBean> list;
    private Context context;
    private int type = 0;
    public HomePageTrumpAdapter(Context context, List<TypePublicBean> list, int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    @Override
    public HomePageTrumpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_man_public_item,parent,false);
        return new HomePageTrumpAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getCoverImageUrl()).into(holder.iv_book);
        holder.tv_book_name.setText(list.get(position).getBookName());
        holder.tv_book_introduce.setText(list.get(position).getIntroduction());
        holder.tv_my_name.setText(list.get(position).getAuthorPenname());//作者
        holder.tv_book_type.setText(list.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_book;
        private TextView tv_book_name,tv_book_introduce,tv_my_name,tv_book_type;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_book_name = itemView.findViewById(R.id.tv_book_name);
            tv_book_introduce = itemView.findViewById(R.id.tv_book_introduce);
            tv_my_name = itemView.findViewById(R.id.tv_my_name);
            tv_book_type = itemView.findViewById(R.id.tv_book_type);
            iv_book = itemView.findViewById(R.id.iv_book);
        }
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private HomePageTrumpAdapter.OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(HomePageTrumpAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }




    public interface OnItemLongClickListener {
        void onClick(int position);
    }

    private HomePageTrumpAdapter.OnItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(HomePageTrumpAdapter.OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}

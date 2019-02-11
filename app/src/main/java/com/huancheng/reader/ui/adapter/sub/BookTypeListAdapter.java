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
 * Created by admin on 2019/1/14.
 */

public class BookTypeListAdapter extends RecyclerView.Adapter<BookTypeListAdapter.ViewHolder> {
    private List<TypePublicBean> list;
    private Context context;
    public BookTypeListAdapter(Context context, List<TypePublicBean> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public BookTypeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_man_public_item,parent,false);
        return new BookTypeListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getCoverImageUrl()).into(holder.iv_book);
        holder.tv_book_name.setText(list.get(position).getBookName());
//        Glide.with(context).load(list.get(position).getImg_icon()).into(holder.iv_book_name);
        holder.tv_book_introduce.setText(list.get(position).getIntroduction());
//        Glide.with(context).load(list.get(position).getMyimg()).into(holder.iv_my_img);
        holder.tv_my_name.setText(list.get(position).getAuthorPenname());
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
        private TextView tv_book_name,tv_book_introduce;
        private ImageView iv_my_img;
        private TextView tv_my_name;
        private TextView tv_book_type;
        private ImageView iv_book_name;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_book = itemView.findViewById(R.id.iv_book);
            tv_book_name = itemView.findViewById(R.id.tv_book_name);
            tv_book_introduce = itemView.findViewById(R.id.tv_book_introduce);
            iv_book_name = itemView.findViewById(R.id.iv_book_name);
            iv_my_img = itemView.findViewById(R.id.iv_my_img);
            tv_my_name = itemView.findViewById(R.id.tv_my_name);
            tv_book_type = itemView.findViewById(R.id.tv_book_type);
        }
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private BookTypeListAdapter.OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(BookTypeListAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemLongClickListener {
        void onClick(int position);
    }

    private BookTypeListAdapter.OnItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(BookTypeListAdapter.OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}

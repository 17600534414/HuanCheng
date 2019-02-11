package com.huancheng.reader.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.BookBean;

import java.util.List;



/**
 * Created by admin on 2018/12/18.
 */

public class BookShelfAdapter extends RecyclerView.Adapter<BookShelfAdapter.ViewHolder> {

    private Context context;
    private List<BookBean> data;
    private int i = 0;

    public BookShelfAdapter(Context context,List<BookBean> data,int i){
        this.context = context;
        this.data = data;
        this.i = i;

    }
    public BookShelfAdapter(){

    }


    @Override
    public BookShelfAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.activity_finishbook_1,parent,false);
            return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (i == 1){
            Glide.with(context).load(data.get(position).getImg()).into(holder.book);
            if(!TextUtils.isEmpty(data.get(position).getBookname())){
                holder.bookname.setText(data.get(position).getBookname());
                holder.bookname.setVisibility(View.VISIBLE);
            }else{
                holder.bookname.setVisibility(View.GONE);
            }

            if(!TextUtils.isEmpty(data.get(position).getBooktype())){
                holder.booktype.setText(data.get(position).getBooktype());
            }else {
                holder.booktype.setVisibility(View.GONE);
            }
            if (Integer.parseInt(data.get(position).getType()) == 2){
                holder.iv_book_type_1.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(position);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        longClickListener.onClick(position);
                    }
                    return true;
                }
            });
        } else if (i == 2){
            if (position == data.size()-1){
                holder.bookname.setVisibility(View.GONE);
                holder.booktype.setVisibility(View.GONE);
                holder.book.setVisibility(View.GONE);
                holder.iv_book_type_1.setVisibility(View.GONE);
            } else if (position <data.size()-1){
                holder.bookname.setText(data.get(position).getBookname());
                holder.booktype.setText(data.get(position).getBooktype());
                Glide.with(context).load(data.get(position).getType()).into(holder.iv_book_type_1);
                Glide.with(context).load(data.get(position).getImg()).into(holder.book);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(position);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        longClickListener.onClick(position);
                    }
                    return true;
                }
            });
        }

    }
    @Override
    public int getItemCount() {
        return (data.size());
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView book;
        private TextView bookname,booktype;
        private ImageView iv_book_type_1;

        public ViewHolder(View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.iv_book_1);
            bookname = itemView.findViewById(R.id.tv_bookname_1);
            booktype = itemView.findViewById(R.id.tv_booktype_1);
            iv_book_type_1 = itemView.findViewById(R.id.iv_book_type_1);

        }
    }
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }




    public interface OnItemLongClickListener {
        void onClick(int position);
    }

    private OnItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}

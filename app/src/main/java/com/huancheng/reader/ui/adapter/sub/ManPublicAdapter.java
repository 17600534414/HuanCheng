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
import com.huancheng.reader.bean.ManPublicBean;
import com.huancheng.reader.bean.TypePublicBean;
import com.huancheng.reader.util.ToastUtil;

import java.util.List;

/**
 * Created by admin on 2018/12/20.
 */

public class ManPublicAdapter extends RecyclerView.Adapter<ManPublicAdapter.ViewHolder> {
    private List<TypePublicBean> list;
    private Context context;
    private int type = 0;
    public ManPublicAdapter(Context context, List<TypePublicBean> list) {
        this.list = list;
        this.context = context;
    }
//    public ManPublicAdapter(Context context, List<ManPublicBean> list,int type) {
//        this.list = list;
//        this.context = context;
//        this.type = type;
//    }
    public ManPublicAdapter(){}

    @Override
    public ManPublicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_man_public_item,parent,false);
        return new ManPublicAdapter.ViewHolder(view);


    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getCoverImageUrl()).into(holder.iv_book);
        holder.tv_book_name.setText(list.get(position).getBookName());
//        Glide.with(context).load(list.get(position).getImg_icon()).into(holder.iv_book_name);
        holder.tv_book_introduce.setText(list.get(position).getIntroduction());
//        Glide.with(context).load(list.get(position).getMyimg()).into(holder.iv_my_img);
        holder.tv_my_name.setText(list.get(position).getAuthorPenname());
        holder.tv_book_type.setText(list.get(position).getCategoryName());
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
            if (type == 0) {

            }
            else if (type == 1){
                tv_book_type.setVisibility(View.VISIBLE);
            }
            else if (type == 2) {
                tv_book_type.setVisibility(View.VISIBLE);
                iv_book_name.setVisibility(View.VISIBLE);
            }
        }
    }
}

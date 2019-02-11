package com.huancheng.reader.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huancheng.reader.R;
import com.huancheng.reader.bean.TypePublicBean;

import java.util.List;

/**
 * Created by admin on 2019/1/10.
 */

public class HomePageNewAdapter extends RecyclerView.Adapter<HomePageNewAdapter.ViewHolder> {
    private List<TypePublicBean> list;
    private Context context;
    private int type = 0;
    public HomePageNewAdapter(Context context, List<TypePublicBean> list, int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }
    public HomePageNewAdapter() {

    }

    @Override
    public HomePageNewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_new,parent,false);
        return new HomePageNewAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getCoverImageUrl()).into(holder.iv_home_img);
        holder.tv_home_book_name.setText(list.get(position).getBookName());
        holder.tv_home_author_name.setText(list.get(position).getAuthorPenname());

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
        if (type == 1){
            return list.size();
        }
        if (type == 2){
            return list.size();
        }
        if (type == 3){
            return list.size()-1;
        }
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_home_img;
        private TextView tv_home_book_name,tv_home_author_name;
        private LinearLayout ll_public_item;

        public ViewHolder(View itemView) {
            super(itemView);
            WindowManager wm = (WindowManager) itemView.getContext()
                    .getSystemService(Context.WINDOW_SERVICE);

            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            iv_home_img = itemView.findViewById(R.id.iv_home_img);
            tv_home_book_name = itemView.findViewById(R.id.tv_home_book_name);
            tv_home_author_name = itemView.findViewById(R.id.tv_home_author_name);
            ll_public_item = itemView.findViewById(R.id.ll_public_item);
            if (type == 1){
                ll_public_item.setMinimumWidth(width/4);
            } else if (type == 3){
                ll_public_item.setMinimumWidth(width/3);
            } else if (type == 2){
                ll_public_item.setMinimumWidth(width/3);
            }

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

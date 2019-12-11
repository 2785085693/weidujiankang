package com.bw.health_homepage.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.health_homepage.R;
import com.wd.mvp.model.bean.SickCircleBean;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * date:2019/12/4
 * author:郑宏宇(Msi)
 * function:
 */
public class FindSickCircleAdapter extends RecyclerView.Adapter {
    private List<SickCircleBean.ResultBean> list;
    private Context context;


    public FindSickCircleAdapter(List<SickCircleBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.findsick_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = new ViewHolder(holder.itemView);
        viewHolder.text_name.setText(list.get(position).getAdoptNickName());
        viewHolder.bingzheng.setText(list.get(position).getDisease());
        viewHolder.keshi.setText(list.get(position).getDepartment());
        viewHolder.xiangqing.setText(list.get(position).getDetail());
        viewHolder.yiyuan.setText(list.get(position).getTreatmentHospital());
        viewHolder.fangfa.setText(list.get(position).getTreatmentProcess());
        viewHolder.dianzan.setText(list.get(position).getAdoptFlag()+"");
        viewHolder.pinglun.setText(list.get(position).getCommentNum()+"");


        Date date = new Date(list.get(position).getTreatmentStartTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        viewHolder.riqi.setText(simpleDateFormat.format(date));

        Glide.with(context).load(list.get(position).getPicture()).into(viewHolder.images);

        viewHolder.comment_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int sickCircleId = list.get(position).getSickCircleId();
//                mOnItemClickListener.onItemClick(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_name;
        TextView bingzheng;
        TextView keshi;
        TextView xiangqing;
        TextView yiyuan;
        TextView riqi;
        TextView fangfa;
        TextView dianzan;
        ImageView images;
        ImageView comment_ImageView;
        TextView pinglun;
        TextView nameTextView;
        TextView textTextView;
        ImageView zanImageView;
        ImageView lowImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_name = itemView.findViewById(R.id.text_name);
            images = itemView.findViewById(R.id.images);
            bingzheng = itemView.findViewById(R.id.bingzheng);
            keshi = itemView.findViewById(R.id.keshi);
            xiangqing = itemView.findViewById(R.id.xiangqing);
            yiyuan = itemView.findViewById(R.id.yiyuan);
            riqi = itemView.findViewById(R.id.riqi);
            fangfa = itemView.findViewById(R.id.fangfa);
            dianzan = itemView.findViewById(R.id.dianzan);
            pinglun = itemView.findViewById(R.id.pinglun);
            comment_ImageView = itemView.findViewById(R.id.comment_ImageView);

            nameTextView = itemView.findViewById(R.id.name_TextView);
            textTextView = itemView.findViewById(R.id.text_TextView);
            zanImageView = itemView.findViewById(R.id.zan_ImageView);
            lowImageView = itemView.findViewById(R.id.low_ImageView);

        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void onItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}

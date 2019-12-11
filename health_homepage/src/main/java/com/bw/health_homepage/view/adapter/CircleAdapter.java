package com.bw.health_homepage.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.health_homepage.R;
import com.bw.health_homepage.view.activity.FindSickCircleActivity;
import com.wd.mvp.model.bean.CircleBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

public class CircleAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<CircleBean.ResultBean> result1;

    private int sickCircleId = 12;
    public CircleAdapter(Context context, List<CircleBean.ResultBean> result1) {
         this.context = context;
         this.result1 = result1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_circle, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.text_name.setText(result1.get(position).getTitle());

        Date date = new Date(result1.get(position).getReleaseTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        viewHolder.text_time.setText(simpleDateFormat.format(date));

        viewHolder.text_edit.setText(result1.get(position).getDetail());
        viewHolder.proposal.setText(result1.get(position).getCollectionNum()+"");
        viewHolder.collection_text.setText(result1.get(position).getCommentNum()+"");

        viewHolder.circlelinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sickCircleId = result1.get(position).getSickCircleId();

                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(sickCircleId);
            }
        });
/*
        Toast.makeText(context, "xx"+viewHolder.text_time, Toast.LENGTH_SHORT).show();
        int sickCircleId = result1.get(position).getSickCircleId();

        mOnItemClickListener.onItemClick(sickCircleId);
        notifyDataSetChanged();

        Intent intent = new Intent(context, FindSickCircleActivity.class);
        context.startActivity(intent);*/


    }

    @Override
    public int getItemCount() {
        return result1.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView xuanshang;
        TextView text_circle;
        TextView collection_text;
        TextView text_name;
        TextView proposal;
        TextView text_time;
        TextView text_edit;
        LinearLayout circlelinear;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xuanshang = itemView.findViewById(R.id.xuanshang);
            text_edit = itemView.findViewById(R.id.text_edit);
            text_time = itemView.findViewById(R.id.text_time);
            text_name = itemView.findViewById(R.id.text_name);
            text_circle = itemView.findViewById(R.id.text_circle);
            collection_text = itemView.findViewById(R.id.collection_text);
            proposal = itemView.findViewById(R.id.proposal);
            circlelinear = itemView.findViewById(R.id.circlelinear);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


}

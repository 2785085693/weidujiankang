package com.bw.health_homepage.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.health_homepage.R;
import com.wd.mvp.model.bean.SearchSickBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SearchSickBean.ResultBean> list;

    public SearchAdapter(Context context, List<SearchSickBean.ResultBean> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder viewHolder = new ViewHolder(holder.itemView);

        viewHolder.text_name.setText(list.get(position).getTitle());

        Date date = new Date(list.get(position).getReleaseTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        viewHolder.text_time.setText(simpleDateFormat.format(date));

        viewHolder.text_edit.setText(list.get(position).getDetail());
        viewHolder.proposal.setText(list.get(position).getCollectionNum()+"");
        viewHolder.collection_text.setText(list.get(position).getCommentNum()+"");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView xuanshang;
        TextView text_circle;
        TextView collection_text;
        TextView text_name;
        TextView proposal;
        TextView text_time;
        TextView text_edit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            xuanshang = itemView.findViewById(R.id.xuanshang);
            text_edit = itemView.findViewById(R.id.text_edit);
            text_time = itemView.findViewById(R.id.text_time);
            text_name = itemView.findViewById(R.id.text_name);
            text_circle = itemView.findViewById(R.id.text_circle);
            collection_text = itemView.findViewById(R.id.collection_text);
            proposal = itemView.findViewById(R.id.proposal);
        }
    }

    private CircleRoomAdapter.OnItemClickListener mOnItemClickListener;

    public void onItemClickListener(CircleRoomAdapter.OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}

package com.bw.health_homepage.view.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.health_homepage.R;
import com.wd.mvp.model.bean.ConsultListBean;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class HomePageConsultListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ConsultListBean.ResultBean> consultListResult;
    public HomePageConsultListAdapter(Context context, List<ConsultListBean.ResultBean> consultListResult) {
            this.context = context;
            this.consultListResult = consultListResult;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_homepageconsultlist,null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.home_page_consultlist_name.setText(consultListResult.get(position).getTitle());
        viewHolder.home_page_consultlist_source.setText(consultListResult.get(position).getSource());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(consultListResult.get(position).getReleaseTime());
        viewHolder.home_page_consultlist_releaseTime.setText(format);
        String[] split = consultListResult.get(position).getThumbnail().split(";");
        for (int i = 0; i <split.length; i++) {
            Glide.with(context).load(split[i]).into(viewHolder.home_page_consultlist_iv);
        }
    }

    @Override
    public int getItemCount() {
        return consultListResult.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView home_page_consultlist_name;
        private final ImageView home_page_consultlist_iv;
        private final TextView home_page_consultlist_releaseTime;
        private final TextView home_page_consultlist_source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_page_consultlist_name = itemView.findViewById(R.id.home_page_consultlist_name);
            home_page_consultlist_iv = itemView.findViewById(R.id.home_page_consultlist_iv);
            home_page_consultlist_releaseTime = itemView.findViewById(R.id.home_page_consultlist_releaseTime);
            home_page_consultlist_source = itemView.findViewById(R.id.home_page_consultlist_source);
        }
    }
}

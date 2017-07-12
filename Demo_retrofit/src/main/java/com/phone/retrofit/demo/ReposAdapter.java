package com.phone.retrofit.demo;

import com.phone.retrofit.demo.bean.Repos;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Phone on 2017/7/12.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    private List<Repos> datas;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ReposAdapter(List<Repos> datas, Context context, OnItemClickListener onItemClickListener) {
        this.datas = datas;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_repos, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReposViewHolder holder, final int position) {
        holder.tv_id.setText(datas
            .get(position)
            .getId() + "");
        holder.tv_name.setText(datas
            .get(position)
            .getName());
        holder.tv_owner.setText(datas
            .get(position)
            .getOwner()
            .getLogin());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(holder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ReposViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id;
        TextView tv_name;
        TextView tv_owner;

        public ReposViewHolder(View itemView) {
            super(itemView);
            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_owner = (TextView) itemView.findViewById(R.id.tv_owner);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}

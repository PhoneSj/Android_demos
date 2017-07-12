package com.phone.demo.realm.adapter;

import com.phone.demo.realm.entity.Dog;
import com.phone.demo.realm.R;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Phone on 2017/7/10.
 */

public class ByDogAdapter extends RecyclerView.Adapter<ByDogAdapter.DogViewHolder> {

    private Context context;
    private List<Dog> datas;
    private int itemLayout;

    public ByDogAdapter(Context context, List<Dog> datas, int itemLayout) {
        this.context = context;
        this.datas = datas;
        this.itemLayout = itemLayout;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(context)
            .inflate(itemLayout, parent, false);
        DogViewHolder holder = new DogViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DogViewHolder holder, final int position) {
        holder.tv_id.setText(datas
            .get(position)
            .getId());
        holder.tv_name.setText(datas
            .get(position)
            .getName());
//        holder.iv_love.setImageResource(R.drawable.like_selector);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class DogViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        ImageView iv_love;

        public DogViewHolder(View view) {
            super(view);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            iv_love = (ImageView) view.findViewById(R.id.iv_like);
        }
    }
}

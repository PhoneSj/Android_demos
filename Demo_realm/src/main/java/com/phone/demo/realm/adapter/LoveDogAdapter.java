package com.phone.demo.realm.adapter;

import com.phone.demo.realm.entity.Dog;
import com.phone.demo.realm.R;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Phone on 2017/7/10.
 */

public class LoveDogAdapter extends RecyclerView.Adapter<LoveDogAdapter.DogViewHolder> {

    private Context context;
    private List<Dog> datas;
    private int itemLayoutId;


    private OnItemClickListener onItemClickListener;

    public LoveDogAdapter(Context context, List<Dog> datas, int itemLayoutId) {
        this.context = context;
        this.datas = datas;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(context)
            .inflate(itemLayoutId, parent, false);
        DogViewHolder holder = new DogViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, final int position) {
        holder.tv_id.setText(datas
            .get(position)
            .getId());
        holder.tv_name.setText(datas
            .get(position)
            .getName());
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class DogViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;

        public DogViewHolder(View view) {
            super(view);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

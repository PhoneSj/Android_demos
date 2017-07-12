package com.phone.demo.realm.adapter;

import com.phone.demo.realm.R;
import com.phone.demo.realm.RealmHelper;
import com.phone.demo.realm.entity.Dog;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * Created by Phone on 2017/7/10.
 */

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    private Context context;
    private List<Dog> datas;
    private int itemLayout;
    private RealmHelper realmHelper;
    //异步处理
    private RealmAsyncTask insertTask;
    private RealmAsyncTask deleteTask;
    private Realm realm;

    public DogAdapter(Context context, List<Dog> datas, int itemLayout) {
        this.context = context;
        this.datas = datas;
        this.itemLayout = itemLayout;
        realmHelper = new RealmHelper();
        realm = Realm.getDefaultInstance();
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
        holder.iv_love.setImageResource(R.drawable.like_selector);
        if (realmHelper.isDogExist(datas
            .get(position)
            .getId())) {
            holder.iv_love.setSelected(true);
        } else {
            holder.iv_love.setSelected(false);
        }
        holder.iv_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.iv_love.isSelected()) {
                    delete(holder, position);
                } else {
                    insert(holder, position);
                }
            }
        });
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

    private void insert(final DogViewHolder holder, final int position) {
        //        //同步添加
        //        holder.iv_love.setSelected(true);
        //        realmHelper.addDog(datas.get(position));
        //异步添加
        insertTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(datas.get(position));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                holder.iv_love.setSelected(true);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                holder.iv_love.setSelected(false);
            }
        });
    }

    private void delete(final DogViewHolder holder, final int position) {
        //        //同步删除
        //        holder.iv_love.setSelected(false);
        //        realmHelper.deleteDog(datas
        //            .get(position)
        //            .getId());
        //异步删除
        deleteTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog dog = realm
                    .where(Dog.class)
                    .equalTo("id", datas
                        .get(position)
                        .getId())
                    .findFirst();
                dog.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                holder.iv_love.setSelected(false);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                holder.iv_love.setSelected(true);
            }
        });
    }

    public void CancelTask() {
        if (insertTask != null && !insertTask.isCancelled()) {
            insertTask.cancel();
        }
        if (deleteTask != null && !deleteTask.isCancelled()) {
            deleteTask.cancel();
        }
    }
}

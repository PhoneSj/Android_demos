package com.phone.demo.realm.activity;

import com.phone.demo.realm.entity.Dog;
import com.phone.demo.realm.R;
import com.phone.demo.realm.RealmHelper;
import com.phone.demo.realm.adapter.LoveDogAdapter;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;

/**
 * Created by Phone on 2017/7/10.
 */

public class QueryAllActivity extends BaseActivity {
    public static final int REQUEST_CODE = 100;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<Dog> datas;
    private LoveDogAdapter adapter;
    private RealmHelper realmHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(toolbar, "查询所有");
        initData();
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_queryall;
    }

    private void initData() {
        realmHelper = new RealmHelper();
        datas = realmHelper.queryAllDog();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new LoveDogAdapter(this, datas, R.layout.item_dog);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new LoveDogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(QueryAllActivity.this, UpdateActivity.class);
                intent.putExtra("id", datas
                    .get(position)
                    .getId());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            datas.clear();
            //                datas = realmHelper.queryAllDog();//这里不能直接接收结果，因为adapter
            // .notifyDataSetChanged()只会更新之前的数据源，这里直接接收改变了数据源的地址，即得不到通知
            List<Dog> dogs = realmHelper.queryAllDog();
            datas.addAll(dogs);
            adapter.notifyDataSetChanged();
        }
    }

}

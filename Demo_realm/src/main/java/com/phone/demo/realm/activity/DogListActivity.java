package com.phone.demo.realm.activity;

import com.phone.demo.realm.R;
import com.phone.demo.realm.adapter.DogAdapter;
import com.phone.demo.realm.entity.Dog;

import java.util.ArrayList;
import java.util.List;

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

public class DogListActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<Dog> mDogs = new ArrayList<>();
    private DogAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(toolbar, "添加/删除数据");
        initData();
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_doglist;
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setToolbar(toolbar, "查询");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new DogAdapter(this, mDogs, R.layout.item_dog);
        recyclerView.setAdapter(adapter);
    }

    protected void setToolbar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        Dog dog1 = new Dog();
        dog1.setName("John");
        dog1.setAge(1);
        dog1.setId("001");
        Dog dog2 = new Dog();
        dog2.setName("Kate");
        dog2.setAge(2);
        dog2.setId("002");
        Dog dog3 = new Dog();
        dog3.setName("Amy");
        dog3.setAge(2);
        dog3.setId("003");
        Dog dog4 = new Dog();
        dog4.setName("Kim");
        dog4.setAge(3);
        dog4.setId("004");
        Dog dog5 = new Dog();
        dog5.setName("Mary");
        dog5.setAge(1);
        dog5.setId("005");
        Dog dog6 = new Dog();
        dog6.setName("Michael");
        dog6.setAge(2);
        dog6.setId("006");
        Dog dog7 = new Dog();
        dog7.setName("James");
        dog7.setAge(3);
        dog7.setId("007");
        Dog dog8 = new Dog();
        dog8.setName("Paul");
        dog8.setAge(1);
        dog8.setId("008");
        Dog dog9 = new Dog();
        dog9.setName("Lily");
        dog9.setAge(1);
        dog9.setId("009");
        Dog dog10 = new Dog();
        dog10.setName("HongSi");
        dog10.setAge(5);
        dog10.setId("010");
        mDogs.add(dog1);
        mDogs.add(dog2);
        mDogs.add(dog3);
        mDogs.add(dog4);
        mDogs.add(dog5);
        mDogs.add(dog6);
        mDogs.add(dog7);
        mDogs.add(dog8);
        mDogs.add(dog9);
        mDogs.add(dog10);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.CancelTask();
        }
    }
}

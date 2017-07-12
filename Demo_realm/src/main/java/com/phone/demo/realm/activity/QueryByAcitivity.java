package com.phone.demo.realm.activity;

import com.phone.demo.realm.entity.Dog;
import com.phone.demo.realm.R;
import com.phone.demo.realm.RealmHelper;
import com.phone.demo.realm.adapter.ByDogAdapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Phone on 2017/7/10.
 */

public class QueryByAcitivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.et_age)
    EditText et_age;
    @BindView(R.id.button_age)
    Button button_age;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RealmHelper realmHelper;
    private List<Dog> datas = new ArrayList<Dog>();
    private ByDogAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(toolbar, "条件查询");
        realmHelper = new RealmHelper();
        initRecyclerView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_queryby;
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new ByDogAdapter(this, datas, R.layout.item_dog);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.button_age)
    public void doClick(View view) {
        String age = et_age
            .getText()
            .toString()
            .trim();
        if (!TextUtils.isEmpty(age)) {
            int number = Integer.parseInt(age);
            List<Dog> dogs = realmHelper.queryDogByAge(number);
            datas.clear();
            datas.addAll(dogs);
            adapter.notifyDataSetChanged();
        }
    }

}

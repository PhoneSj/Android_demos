package com.phone.demo.realm.activity;

import com.phone.demo.realm.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Phone on 2017/7/10.
 */

public class QueryActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(toolbar, "查询");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_query;
    }

    @OnClick({R.id.button_queryAll, R.id.button_queryBy, R.id.button_statistics})
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.button_queryAll:
                startActivity(new Intent(QueryActivity.this, QueryAllActivity.class));
                break;
            case R.id.button_queryBy:
                startActivity(new Intent(QueryActivity.this, QueryByAcitivity.class));
                break;
            case R.id.button_statistics:
                startActivity(new Intent(QueryActivity.this, StatisticalActivity.class));
                break;
        }
    }
}

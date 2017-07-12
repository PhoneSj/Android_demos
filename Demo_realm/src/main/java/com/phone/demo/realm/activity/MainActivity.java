package com.phone.demo.realm.activity;

import com.phone.demo.realm.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;//不能为private或static修饰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.button_origin, R.id.button_query})
    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.button_origin:
                startActivity(new Intent(MainActivity.this, DogListActivity.class));
                break;
            case R.id.button_query:
                startActivity(new Intent(MainActivity.this, QueryActivity.class));
                break;
        }
    }
}

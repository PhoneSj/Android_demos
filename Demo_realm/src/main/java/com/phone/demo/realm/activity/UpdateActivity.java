package com.phone.demo.realm.activity;

import com.phone.demo.realm.entity.Dog;
import com.phone.demo.realm.R;
import com.phone.demo.realm.RealmHelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class UpdateActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.button_ok)
    Button button_ok;

    private RealmHelper realmHelper;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(toolbar, "修改");
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update;
    }

    private void initData() {
        id = getIntent().getStringExtra("id");
        realmHelper = new RealmHelper();
        Dog dog = realmHelper.queryDogById(id);
        et_name.setHint(dog.getName());
    }

    @OnClick(R.id.button_ok)
    public void doClick(View view) {
        String name = et_name
            .getText()
            .toString()
            .trim();
        if (!TextUtils.isEmpty(name)) {
            realmHelper.updateDog(id, name);
            setResult(RESULT_OK);
            finish();
        }
    }
}

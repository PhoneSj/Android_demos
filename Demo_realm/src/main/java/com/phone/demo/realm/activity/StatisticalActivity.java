package com.phone.demo.realm.activity;

import com.phone.demo.realm.entity.Dog;
import com.phone.demo.realm.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import io.realm.Realm;

/**
 * Created by Phone on 2017/7/10.
 */

public class StatisticalActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.tv_max)
    TextView tv_max;
    @BindView(R.id.tv_min)
    TextView tv_min;
    @BindView(R.id.tv_sum)
    TextView tv_sum;
    @BindView(R.id.tv_avg)
    TextView tv_avg;

    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(toolbar, "统计");
        realm = Realm.getDefaultInstance();
        setMax();
        setMin();
        setSum();
        setAvg();
    }

    private void setSum() {
        Number sum = realm
            .where(Dog.class)
            .sum("age");
        int sumAge = sum.intValue();
        tv_sum.setText("总年龄：" + sumAge);
    }

    private void setAvg() {
        Number avg = realm
            .where(Dog.class)
            .average("age");
        double avgAge = avg.doubleValue();
        tv_avg.setText("平均年龄：" + avgAge);
    }

    private void setMin() {
        Number min = realm
            .where(Dog.class)
            .min("age");
        int minAge = min.intValue();
        tv_min.setText("最小年龄：" + minAge);
    }

    private void setMax() {
        Number max = realm
            .where(Dog.class)
            .max("age");
        int maxAge = max.intValue();
        tv_max.setText("最大年龄：" + maxAge);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statistical;
    }
}

package com.phone.demo;

import javax.inject.Inject;
import javax.inject.Named;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    @Inject
    Cloth cloth;//不能是private protected修改
    @Inject
    Shoe shoe;
    @Inject
    Clothes clothes;
    @Inject
    @Named("red")
    Cloth redCloth;
    @Inject
    @Named("blue")
    Cloth blueCloth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        MainComponent build = DaggerMainComponent
            .builder()
            .mainModule(new MainModule())
            .build();
        build.inject(this);
        StringBuilder sb = new StringBuilder();
        sb.append("I have " + cloth + " and " + shoe + " and " + clothes);
        sb.append("\n");
        sb.append(redCloth + "," + blueCloth);
        sb.append("\n");
        sb.append(cloth == clothes.getCloth());
        tv.setText(sb.toString());
    }
}

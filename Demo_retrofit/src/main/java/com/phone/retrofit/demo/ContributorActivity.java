package com.phone.retrofit.demo;

import com.phone.retrofit.demo.bean.Contributor;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Phone on 2017/7/12.
 */

public class ContributorActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tv_Content;

    Call<List<Contributor>> contributorCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contirbutor);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String owner = intent.getStringExtra("owner");
        GitHubService service = ApiUtil.getGitHubService();
        contributorCall = service.listConstributor(owner, name);
        //        //同步请求
        //        try {
        //            Response<List<Contributor>> response = contributorCall.execute();
        //            showData(response);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //异步请求
        contributorCall.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                Toast
                    .makeText(ContributorActivity.this, "下载成功", Toast.LENGTH_SHORT)
                    .show();
                showData(response);
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                Toast
                    .makeText(ContributorActivity.this, "下载失败", Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }

    private void showData(Response<List<Contributor>> response) {
        if (response.code() == 200) {
            List<Contributor> datas = response.body();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (Contributor contributor : datas) {
                sb.append("==================" + i + "=================\n");
                sb.append(contributor.getId() + "\n");
                sb.append(contributor.getLogin() + "\n");
                sb.append(contributor.getContributions() + "\n");
                i++;
            }
            tv_Content.setText(sb.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (contributorCall != null && !contributorCall.isCanceled()) {
            contributorCall.cancel();
        }
    }
}

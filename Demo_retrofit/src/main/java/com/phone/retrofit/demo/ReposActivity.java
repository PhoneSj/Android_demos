package com.phone.retrofit.demo;

import com.phone.retrofit.demo.bean.Repos;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Phone on 2017/7/12.
 */

public class ReposActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Call<List<Repos>> reposes;
    ReposAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        GitHubService service = ApiUtil.getGitHubService();
        reposes = service.listRepos("PhoneSj");
        reposes.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                Toast
                    .makeText(ReposActivity.this, "下载成功", Toast.LENGTH_SHORT)
                    .show();
                if (response.code() == 200) {
                    LayoutManager manager = new LinearLayoutManager(ReposActivity.this);
                    recyclerView.setLayoutManager(manager);
                    final List<Repos> datas = response.body();
                    adapter = new ReposAdapter(datas, ReposActivity.this, new ReposAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(ReposActivity.this, ContributorActivity.class);
                            intent.putExtra("name", datas
                                .get(position)
                                .getName());
                            intent.putExtra("owner", datas
                                .get(position)
                                .getOwner()
                                .getLogin());
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                Toast
                    .makeText(ReposActivity.this, "下载失败", Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reposes != null && !reposes.isCanceled()) {
            reposes.cancel();
        }
    }
}

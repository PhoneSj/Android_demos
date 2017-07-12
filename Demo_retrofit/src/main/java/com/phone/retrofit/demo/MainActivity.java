package com.phone.retrofit.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button0)
    Button button0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.button0)
    public void onViewClicked(View view) {
        startActivity(new Intent(this, ReposActivity.class));
        //        Retrofit retrofit = new Retrofit.Builder()
        //            .baseUrl("https://api.github.com/")
        //            .addConverterFactory(GsonConverterFactory.create())
        //            .build();
        //        GitHubService service = retrofit.create(GitHubService.class);
        //        Call<List<Repos>> reposCall = service.listRepos("PhoneSj");
        //        reposCall.enqueue(new Callback<List<Repos>>() {
        //            @Override
        //            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
        //                Log.i("phone", "onResponse");
        //                StringBuilder sb = new StringBuilder();
        //                for (Repos repos : response.body()) {
        //                    sb.append(repos.toString() + "\n");
        //                }
        //                Log.i("phone", sb.toString());
        //            }
        //
        //            @Override
        //            public void onFailure(Call<List<Repos>> call, Throwable t) {
        //                Log.i("phone", "onFailure:" + t.toString());
        //                Log.i("phone", "url:" + call
        //                    .request()
        //                    .url()
        //                    .toString());
        //            }
        //        });
        //        //https://api.github.com/repos/{owner}/{repo}/contributors
        //        //https://api.github.com/repos/PhoneSj/SwipeLayout/contributors
        //        Call<List<Contributor>> contributorCall = service.requestConstributor("PhoneSj", "SwipeLayout");
        //        contributorCall.enqueue(new Callback<List<Contributor>>() {
        //            @Override
        //            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
        //                Log.i("phone", "onResponse");
        //                StringBuilder sb = new StringBuilder();
        //                for (Contributor con : response.body()) {
        //                    sb.append(con.toString() + "\n");
        //                }
        //                Log.i("phone", sb.toString());
        //            }
        //
        //            @Override
        //            public void onFailure(Call<List<Contributor>> call, Throwable t) {
        //                Log.i("phone", "onFailure:" + t.toString());
        //                Log.i("phone", "url:" + call
        //                    .request()
        //                    .url()
        //                    .toString());
        //            }
        //        });
    }
}

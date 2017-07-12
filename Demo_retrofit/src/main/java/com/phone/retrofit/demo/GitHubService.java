package com.phone.retrofit.demo;

import com.phone.retrofit.demo.bean.Contributor;
import com.phone.retrofit.demo.bean.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Phone on 2017/7/11.
 */

public interface GitHubService {

    //https://api.github.com/users/{user}/repos
    @GET("users/{user}/repos")
    Call<List<Repos>> listRepos(@Path("user") String user);

    //https://api.github.com/repos/{owner}/{repo}/contributors
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> listConstributor(@Path("owner") String owner, @Path("repo") String repo);

}

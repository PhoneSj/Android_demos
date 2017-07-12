package com.phone.retrofit.demo;

/**
 * Created by Phone on 2017/7/12.
 */

public class ApiUtil {
    public static final String BASE_URL = "https://api.github.com/";

    public static GitHubService getGitHubService() {
        return RetrofitClient
            .getClient(BASE_URL)
            .create(GitHubService.class);
    }
}

package com.payconiq.mohamedassignment.data.remote.service;


import com.payconiq.mohamedassignment.data.remote.dto.Repo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mohamed Hemdan on 9/9/2017
 */

public interface DataService {
    @GET("users/JakeWharton/repos")
    Call<ArrayList<Repo>> fetchRepos(@Query("page") String page, @Query("per_page") int pageSize);
}

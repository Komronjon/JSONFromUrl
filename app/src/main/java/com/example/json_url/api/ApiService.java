package com.example.json_url.api;

import com.example.json_url.pojo.Emploee;
import com.example.json_url.pojo.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("testTask.json")
//    @Query("api_key")
//    int apiKey, @Query("lang") String lang
    Observable<EmployeeResponse> getEmploees();

}

package com.example.json_url.api;

import com.example.json_url.pojo.Emploee;
import com.example.json_url.pojo.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<EmployeeResponse> getEmploees();
}

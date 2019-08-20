package com.davidargote.appconsumoretrofit.Interfaces;

import com.davidargote.appconsumoretrofit.model.Employees;
import com.davidargote.appconsumoretrofit.model.PostEmployee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApiEmployees {

    @GET("employees")
    Call<List<Employees>> getEmployees();

    @POST("create")
    Call<PostEmployee> createUser(@Body PostEmployee postEmployee);

}

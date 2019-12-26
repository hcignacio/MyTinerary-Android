package com.example.kotlintest.Networking

import com.example.kotlintest.Model.City
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("cities")
    fun getCities(): Call<List<City>>
}
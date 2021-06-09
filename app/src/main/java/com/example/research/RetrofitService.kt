package com.example.research

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("top-headlines?country=kr&apiKey=250795ae77b84dc3ae51e19457a07e69")
    fun getNewsData(): Call<List<FirstData>>

    @GET("top-headlines?country=kr&apiKey=250795ae77b84dc3ae51e19457a07e69")
    fun getNews(): Call<FirstData>
}
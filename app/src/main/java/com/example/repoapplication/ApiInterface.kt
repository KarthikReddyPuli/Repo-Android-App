package com.example.repoapplication

import retrofit2.http.GET

interface ApiInterface {
    @GET("orgs/fossasia/repos")
    fun getdata(): retrofit2.Call<List<RepoItem>>
}
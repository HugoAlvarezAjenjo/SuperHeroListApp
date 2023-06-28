package com.revan.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/281757827717963/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>
}
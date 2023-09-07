package com.example.mvvmrecipeapp.data.api

import com.example.mvvmrecipeapp.data.model.RecipeSearchResponse
import com.example.mvvmrecipeapp.utils.AppConstant.TOKEN
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    //https://food2fork.ca/api/recipe/search/?page=2&query=onion

    @GET("search")
    @Headers("Authorization: $TOKEN")
    suspend fun search(@Query("page") page:String,
                        @Query("query") query: String) : RecipeSearchResponse
}
package com.example.mvvmrecipeapp.data.repository

import com.example.mvvmrecipeapp.data.api.ApiService
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeSearchRepo @Inject constructor(private val apiService: ApiService) {

    fun getRecipe(page: String, query: String): Flow<List<RecipeNetworkEntity>> {
        return flow{
            emit(apiService.search(page, query))
        }.map {
            it.recipes
        }
    }

}
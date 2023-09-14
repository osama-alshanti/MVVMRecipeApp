package com.example.mvvmrecipeapp.data.local.doa

import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeDaoRepository
@Inject constructor(private val recipeDao: RecipeDao) {

    fun getRecipeById(id: Int): Flow<RecipeNetworkEntity?> = flow {
        emit(recipeDao.getRecipeById(id))
    }

    suspend fun addRecipe(recipe: RecipeNetworkEntity): Flow<RecipeNetworkEntity> = flow {
        recipeDao.insertRecipe(recipe)
        emit(recipe)
    }

    suspend fun deleteRecipe(recipe: RecipeNetworkEntity): Flow<Unit> = flow {
        recipeDao.deleteRecipe(recipe)
    }
}
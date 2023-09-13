package com.example.mvvmrecipeapp.data.local.doa

import androidx.room.*
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeNetworkEntity)

    @Query("SELECT * FROM recipenetworkentity WHERE :id == pk")
    fun getRecipeById(id: Int): RecipeNetworkEntity?

    @Delete
    suspend fun deleteRecipe(recipe: RecipeNetworkEntity)
}
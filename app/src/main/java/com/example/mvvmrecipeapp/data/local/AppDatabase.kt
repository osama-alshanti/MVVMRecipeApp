package com.example.mvvmrecipeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmrecipeapp.data.local.doa.RecipeDao
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity

@TypeConverters(Converters::class)
@Database(entities = [RecipeNetworkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

}
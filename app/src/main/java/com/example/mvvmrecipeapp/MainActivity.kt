package com.example.mvvmrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmrecipeapp.ui.base.UiState
import com.example.mvvmrecipeapp.ui.recipe.RecipeViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val recipeViewModel: RecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recipeViewModel.getRecipe("1", "")
        lifecycleScope.launchWhenStarted {
            recipeViewModel.recipeStateFlow.collect{
                when(it){
                    is UiState.Loading -> {
                        Log.d(TAG, "Loading: ")
                    }
                    is UiState.Success ->{
                        Log.d(TAG, "Success:")
                    }
                    is UiState.Error ->{
                        Log.d(TAG, "Error: ")
                    }
                }
            }
        }

    }
}
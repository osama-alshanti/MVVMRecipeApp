package com.example.mvvmrecipeapp.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.data.local.doa.RecipeDaoRepository
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import com.example.mvvmrecipeapp.data.repository.RecipeSearchRepo
import com.example.mvvmrecipeapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val recipeRepo: RecipeSearchRepo): ViewModel(){

    private val _recipeMutableState = MutableStateFlow<UiState<List<RecipeNetworkEntity>>>(UiState.Loading)
    val recipeStateFlow: StateFlow<UiState<List<RecipeNetworkEntity>>> = _recipeMutableState

     fun getRecipe(page:String, query: String){
         viewModelScope.launch {
             recipeRepo.getRecipe(page, query)
                 .catch {
                     _recipeMutableState.value = UiState.Error(it.message.toString())
                 }
                 .collect{
                     _recipeMutableState.value = UiState.Success(it)
                 }
         }

    }
}
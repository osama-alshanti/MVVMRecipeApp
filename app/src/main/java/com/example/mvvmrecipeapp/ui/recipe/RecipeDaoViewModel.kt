package com.example.mvvmrecipeapp.ui.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.data.local.doa.RecipeDaoRepository
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import com.example.mvvmrecipeapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDaoViewModel @Inject constructor(private val recipeDaoRepository: RecipeDaoRepository): ViewModel(){

    private val _uiState = MutableStateFlow<UiState<RecipeNetworkEntity>>(UiState.Loading)

    val uiState: StateFlow<UiState<RecipeNetworkEntity>> = _uiState


    fun getRecipe(id: Int){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            recipeDaoRepository.getRecipeById(id)
                .flowOn(Dispatchers.IO)
                .catch {
                    _uiState.value = UiState.Error(it.toString())
            }
                .collect{
                    it?.let {
                        _uiState.value = UiState.Success(it)
                    }
            }
        }
    }

    fun addRecipe(recipe: RecipeNetworkEntity){
        viewModelScope.launch {
            recipeDaoRepository.addRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: RecipeNetworkEntity){
        viewModelScope.launch {
            recipeDaoRepository.deleteRecipe(recipe)
        }
    }
}
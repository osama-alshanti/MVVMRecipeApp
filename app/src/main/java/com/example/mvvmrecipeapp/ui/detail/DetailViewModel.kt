package com.example.mvvmrecipeapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import com.example.mvvmrecipeapp.data.repository.RecipeSearchRepo
import com.example.mvvmrecipeapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo: RecipeSearchRepo) : ViewModel() {

    private val _getRecipeMutableState = MutableStateFlow<UiState<List<RecipeNetworkEntity>>>(UiState.Loading)
    val getRecipeStateFlow: StateFlow<UiState<List<RecipeNetworkEntity>>> = _getRecipeMutableState

    fun getRecipeById(id: String){
        viewModelScope.launch {
            repo.getRecipeById(id).catch {
                _getRecipeMutableState.value = UiState.Error(it.message.toString())
            }.collect{
                _getRecipeMutableState.value = UiState.Success(arrayListOf(it))
            }
        }
    }
}
package com.example.mvvmrecipeapp.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.data.local.doa.RecipeDaoRepository
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import com.example.mvvmrecipeapp.data.repository.RecipeSearchRepo
import com.example.mvvmrecipeapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val apiRepo: RecipeSearchRepo,
                                                private val dbRepo: RecipeDaoRepository) : ViewModel() {

    private val _getRecipeMutableState = MutableStateFlow<UiState<RecipeNetworkEntity>>(UiState.Loading)
    val getRecipeStateFlow: StateFlow<UiState<RecipeNetworkEntity>> = _getRecipeMutableState

    fun getRecipeById(id: Int){
        viewModelScope.launch(Dispatchers.Main) {
            _getRecipeMutableState.value = UiState.Loading

            dbRepo.getRecipeById(id)
                .flatMapConcat {recipeFromDb ->

                    if (recipeFromDb == null){
                         return@flatMapConcat apiRepo.getRecipeById(id.toString())
                            .flatMapConcat { recipesToInsertInDB ->
                                dbRepo.addRecipe(recipesToInsertInDB)
                                flow {
                                    emit(recipesToInsertInDB)
                                }
                            }
                    }else{
                         return@flatMapConcat flow {
                            emit(recipeFromDb)
                        }
                    }
                }
                .flowOn(Dispatchers.IO)
                .catch {
                    _getRecipeMutableState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _getRecipeMutableState.value = UiState.Success(it)
                }

        }
    }

    fun flowFrom(recipe: RecipeNetworkEntity) = flowOf(1, 2, 3)
        .map { "${it}_${recipe.pk} " }
}
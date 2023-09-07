package com.example.mvvmrecipeapp.ui.base

sealed interface UiState <out T>{

    object Loading: UiState<Nothing>

    data class Success<T> (val data: List<T>): UiState<List<T>>

    data class Error (val msg: String): UiState<Nothing>
}
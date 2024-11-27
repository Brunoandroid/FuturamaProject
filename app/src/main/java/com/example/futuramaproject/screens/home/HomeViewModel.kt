package com.example.futuramaproject.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _isShowDialog = MutableStateFlow(false)
    val isShowDialog: StateFlow<Boolean> get() = _isShowDialog

    fun showDialog() {
        _isShowDialog.value = true
    }

    fun hideDialog() {
        _isShowDialog.value = false
    }
}
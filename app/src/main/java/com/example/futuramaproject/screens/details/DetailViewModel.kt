package com.example.futuramaproject.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futuramaproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _items = MutableLiveData<String?>()
    val items: LiveData<String?> = _items

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = repository.getItems()
            _items.postValue(result.toString())
            _isLoading.postValue(false)
        }
    }

}
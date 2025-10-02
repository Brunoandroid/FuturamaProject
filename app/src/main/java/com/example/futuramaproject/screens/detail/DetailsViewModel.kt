package com.example.futuramaproject.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _character = MutableLiveData<CharacterItem?>()
    val character: LiveData<CharacterItem?> = _character

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchDetails()
    }

    private fun fetchDetails() {
        val id: Int? = savedStateHandle["id"]
        if (id == null) {
            _character.postValue(null)
            return
        }
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val characterItem = repository.getCharacterDetails(id)
                _character.postValue(characterItem)
            } catch (e: Exception) {
                _character.postValue(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
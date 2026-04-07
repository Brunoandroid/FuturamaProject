package com.example.futuramaproject.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailsUiState(
    val character: CharacterItem? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    init {
        fetchDetails()
    }

    private fun fetchDetails() {
        val id: Int? = savedStateHandle["id"]
        if (id == null) {
            _uiState.update { it.copy(isError = false, isLoading = false) }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isError = false, isLoading = true) }
            try {
                val characterItem = repository.getCharacterDetails(id)
                _uiState.update { it.copy(character = characterItem, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isError = true, isLoading = false) }
            }
        }
    }
}
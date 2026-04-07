package com.example.futuramaproject.screens.initial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.futuramaproject.data.model.CharacterItem
import com.example.futuramaproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val items: Flow<PagingData<CharacterItem>> = repository.getCharacters()
        .cachedIn(viewModelScope)

}
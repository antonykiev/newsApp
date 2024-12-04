package com.petproject.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.news.ui.screenstate.ScreenState
import com.petproject.news.domain.usecases.ObserveArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val observeArticlesUseCase: ObserveArticlesUseCase,
) : ViewModel() {

    private val initialState = ScreenState.Loading
    private val _state = MutableStateFlow<ScreenState>(initialState)
    val state: StateFlow<ScreenState> = _state.asStateFlow()

    fun loadInitialState() {
        viewModelScope.launch {
            observeArticlesUseCase()
                .map(ScreenState::Loaded)
                .collect(_state::emit)
        }
    }
}
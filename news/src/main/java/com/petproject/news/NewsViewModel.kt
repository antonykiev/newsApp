package com.petproject.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.news.ui.screenstate.NewsScreenState
import com.petproject.news.usecases.ObserveArticlesUseCase
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

    private val initialState = NewsScreenState.Loading
    private val _state = MutableStateFlow<NewsScreenState>(initialState)
    val state: StateFlow<NewsScreenState> = _state.asStateFlow()

    fun loadInitialState() {
        viewModelScope.launch {
            observeArticlesUseCase.invoke()
                .map(NewsScreenState::Loaded)
                .collect(_state::emit)
        }
    }
}
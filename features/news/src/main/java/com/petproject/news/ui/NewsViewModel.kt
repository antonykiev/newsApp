package com.petproject.news.ui

import android.app.DownloadManager.Query
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.news.ui.screenstate.ScreenState
import com.petproject.news.domain.usecases.ObserveArticlesUseCase
import com.petproject.news.domain.usecases.ScreenStateUseCase
import com.petproject.news.ui.screenstate.SearchBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val screenStateUseCase: ScreenStateUseCase,
    private val observeArticlesUseCase: ObserveArticlesUseCase,
) : ViewModel() {

    val state: StateFlow<ScreenState> = screenStateUseCase.observeScreenState()

    fun loadInitialState() {
        viewModelScope.launch {
//            observeArticlesUseCase()
//                .map(ScreenState::Loaded)
//                .collect(_state::emit)
        }
    }

    fun onQueryChange(query: String) {
        screenStateUseCase.onQueryChange(query)
    }
}
package com.petproject.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.news.ui.screenstate.ScreenState
import com.petproject.news.domain.usecases.ObserveArticlesUseCase
import com.petproject.news.domain.usecases.ScreenStateUseCase
import com.petproject.news.ui.screenstate.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
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
//        viewModelScope.launch {
//            observeArticlesUseCase("bitcoin")
//                .map(ListState::Loaded)
//                .collect(screenStateUseCase::updateListState)
//        }
    }

    fun onQueryChange(query: String) {
        screenStateUseCase.onQueryChange(query)
    }

    fun onSearch(query: String) {
        viewModelScope.launch {
            observeArticlesUseCase(query)
                .map(ListState::Loaded)
                .collect(screenStateUseCase::updateListState)
        }
        screenStateUseCase.updateActiveState(false)
    }

    fun onActiveChange(isActive: Boolean) {
        screenStateUseCase.updateActiveState(isActive)
    }
}
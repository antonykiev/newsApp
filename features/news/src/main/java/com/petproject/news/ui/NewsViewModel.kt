package com.petproject.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.core.data.Query
import com.petproject.news.domain.usecases.AddQueryUseCase
import com.petproject.news.domain.usecases.ObserveArticlesUseCase
import com.petproject.news.domain.usecases.QueryHistoryUseCase
import com.petproject.news.domain.usecases.ScreenStateUseCase
import com.petproject.news.domain.usecases.UpdateQueryUseCase
import com.petproject.news.ui.screenstate.ListState
import com.petproject.news.ui.screenstate.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val screenStateUseCase: ScreenStateUseCase,
    private val observeArticlesUseCase: ObserveArticlesUseCase,
    private val queryHistoryUseCase: QueryHistoryUseCase,
    private val addQueryUseCase: AddQueryUseCase,
    private val updateQueryUseCase: UpdateQueryUseCase,
) : ViewModel() {

    val state: StateFlow<ScreenState> = screenStateUseCase.observeScreenState()

    fun onQueryChange(query: String) {
        screenStateUseCase.onQueryChange(query)
    }

    fun onSearch(query: String) {
        viewModelScope.launch {
            addQueryUseCase(query)
        }
        viewModelScope.launch {
            observeArticles(query)
        }
        screenStateUseCase.updateActiveState(false)
    }

    fun onActiveChange(isActive: Boolean) {
        screenStateUseCase.updateActiveState(isActive)
        viewModelScope.launch {
            val queryHistory = queryHistoryUseCase(isActive).getOrNull()
            queryHistory?.let {
                screenStateUseCase.updateQueryHistory(it)
            }
        }
    }

    fun onQuerySelected(query: Query) {
        screenStateUseCase.onQueryChange(query.text)
        viewModelScope.launch {
            updateQueryUseCase(query)
        }
        viewModelScope.launch {
            observeArticles(query.text)
        }
        screenStateUseCase.updateActiveState(false)
    }

    private suspend fun observeArticles(query: String) {
        observeArticlesUseCase(query)
            .map(ListState::Loaded)
            .collect(screenStateUseCase::updateListState)
    }
}
package com.petproject.news.domain.usecases

import com.petproject.core.data.Query
import com.petproject.core.presentation.BaseScreenStateUseCase
import com.petproject.news.ui.screenstate.ListState
import com.petproject.news.ui.screenstate.ScreenState
import com.petproject.news.ui.screenstate.SearchBarState
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ScreenStateUseCase @Inject constructor() : BaseScreenStateUseCase<ScreenState>() {

    override fun getInitialState(): ScreenState {
        return ScreenState(
            searchBarState = SearchBarState(
                query = DEFAULT_QUERY,
                active = DEFAULT_ACTIVE,
                queryHistory = DEFAULT_QUERY_HISTORY
            ),
            listState = ListState.Initial
        )
    }

    fun onQueryChange(query: String) {
        screenStateFlow.update {
            it.copy(
                searchBarState = it.searchBarState.copy(
                    query = query
                )
            )
        }
    }

    fun updateListState(listState: ListState) {
        screenStateFlow.update {
            it.copy(
                listState = listState
            )
        }
    }

    fun updateActiveState(active: Boolean) {
        screenStateFlow.update {
            it.copy(
                searchBarState = it.searchBarState.copy(
                    active = active
                )
            )
        }
    }

    fun updateQueryHistory(queryHistory: List<Query>) {
        screenStateFlow.update {
            it.copy(
                searchBarState = it.searchBarState.copy(
                    queryHistory = queryHistory
                )
            )
        }
    }

    companion object Constants {
        private const val DEFAULT_QUERY = ""
        private const val DEFAULT_ACTIVE = false
        private val DEFAULT_QUERY_HISTORY = emptyList<Query>()
    }
}
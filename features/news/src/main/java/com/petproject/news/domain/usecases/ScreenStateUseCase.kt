package com.petproject.news.domain.usecases

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
                query = "",
                active = true
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
}
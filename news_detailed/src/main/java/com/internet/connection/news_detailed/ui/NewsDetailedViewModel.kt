package com.internet.connection.news_detailed.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.internet.connection.news_detailed.domain.data.ArticlePresentation
import com.internet.connection.news_detailed.domain.usecase.LoadPresentationStateUseCase
import com.internet.connection.news_detailed.ui.screenstate.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailedViewModel @Inject constructor(
    private val loadPresentationStateUseCase: LoadPresentationStateUseCase,
) : ViewModel() {

    private val initialState = ScreenState.Loading
    private val _state = MutableStateFlow<ScreenState>(initialState)
    val state: StateFlow<ScreenState> = _state.asStateFlow()

    fun load(articleId: Long) {
        viewModelScope.launch {
            loadPresentationStateUseCase(articleId)
                .fold(
                    fun(presentation: ArticlePresentation) {
                        _state.emit(ScreenState.Loaded(presentation))
                    },
                    fun(error: Throwable) {
                        _state.emit(ScreenState.Error(error))
                    }
                )
        }
    }
}
package com.petproject.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.core.ScreenState
import com.petproject.news.ui.NewsScreenState
import com.petproject.news_list.usecase.ScreenStateUseCase
import com.petproject.server.ServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val screenState: ScreenStateUseCase,
    private val serverRepository: ServerRepository,
) : ViewModel(),
    ScreenState<NewsScreenState> by screenState {

    init {
        viewModelScope.launch {
            screenState()
        }
        viewModelScope.launch {
            val response = serverRepository.everything("bitcoin")
            Log.d("NewsViewModel", "response: $response")
        }
    }
}
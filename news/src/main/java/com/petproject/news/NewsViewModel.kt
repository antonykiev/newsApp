package com.petproject.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.news.ui.mappers.ArticleToNewsPresentationMapper
import com.petproject.news.ui.screenstate.ErrorLoading
import com.petproject.news.ui.screenstate.Loaded
import com.petproject.news.ui.screenstate.Loading
import com.petproject.news.ui.screenstate.NewsScreenState
import com.petproject.news.ui.screenstate.StateArgs
import com.petproject.server.ServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val serverRepository: ServerRepository,
) : ViewModel() {

    private val initialState = Loading(StateArgs.LoadingArgs)
    private val _state = MutableStateFlow<NewsScreenState>(initialState)
    val state: StateFlow<NewsScreenState> = _state.asStateFlow()

    private var isLoaded = AtomicBoolean(false)

    fun loadInitialState() {
        if (isLoaded.get()) return

        viewModelScope.launch {
            _state.value = loadArticles()
            isLoaded.set(true)
        }
    }

    private suspend fun loadArticles(): NewsScreenState {
        val response = serverRepository.everything("bitcoin")
        return response.fold(
            onSuccess = {
                val news = it.articles.map {
                    ArticleToNewsPresentationMapper(it).newsPresentation()
                }
                Loaded(StateArgs.LoadedArgs(news))
            },
            onFailure = {
                ErrorLoading(StateArgs.ErrorLoadingArgs(it))
            }
        )
    }
}
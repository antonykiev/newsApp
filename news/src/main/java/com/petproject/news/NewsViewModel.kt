package com.petproject.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.news.ui.mappers.ArticleToNewsPresentationMapper
import com.petproject.news.ui.screenstate.ErrorLoading
import com.petproject.news.ui.screenstate.Loaded
import com.petproject.news.ui.screenstate.Loading
import com.petproject.news.ui.screenstate.NewsScreenState
import com.petproject.news.ui.screenstate.StateArgs
import com.petproject.core.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val serverRepository: ArticleRepository,
) : ViewModel() {

    private val initialState = Loading(StateArgs.LoadingArgs)
    private val _state = MutableStateFlow<NewsScreenState>(initialState)
    val state: StateFlow<NewsScreenState> = _state.asStateFlow()

    fun loadInitialState() {
        viewModelScope.launch {
            serverRepository.everything("bitcoin")
                .collect { result ->
                    _state.value = result.fold(
                        onSuccess = {
                            val news = it.map {
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
    }
}
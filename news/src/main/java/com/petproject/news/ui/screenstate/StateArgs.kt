package com.petproject.news.ui.screenstate

import com.petproject.news.ui.data.NewsPresentation

interface StateArgs {

    data object LoadingArgs : StateArgs

    class ErrorLoadingArgs(
        val throwable: Throwable,
    ) : StateArgs

    data class LoadedArgs(
        val news: List<NewsPresentation>,
    ) : StateArgs

}
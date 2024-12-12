package com.petproject.core.presentation

import androidx.annotation.CallSuper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseScreenStateUseCase<T> {

    protected val screenStateFlow by lazy(LazyThreadSafetyMode.PUBLICATION) {
        MutableStateFlow(getInitialState())
    }

    fun observeScreenState(): StateFlow<T> = screenStateFlow

    protected abstract fun getInitialState(): T

    @CallSuper
    protected fun updateState(state: T) {
        screenStateFlow.update {
            state
        }
    }
}
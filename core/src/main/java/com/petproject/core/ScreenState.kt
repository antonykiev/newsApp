package com.petproject.core

import kotlinx.coroutines.flow.StateFlow

interface ScreenState<T> {
    val state: StateFlow<T>
}
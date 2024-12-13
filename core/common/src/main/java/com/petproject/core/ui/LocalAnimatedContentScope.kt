package com.petproject.core.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAnimatedContentScope = staticCompositionLocalOf<AnimatedContentScope> {
    error("No AnimatedContentScope provided")
}


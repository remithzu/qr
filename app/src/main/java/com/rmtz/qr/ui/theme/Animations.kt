package com.rmtz.qr.ui.theme

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object Animations {
    val SlideIn: EnterTransition
        get() {
            return slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        }

    val SlideOut: ExitTransition
        get() {
            return slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(500)
            )
        }
}
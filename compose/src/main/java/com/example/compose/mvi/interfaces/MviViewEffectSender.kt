package com.example.compose.mvi.interfaces

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface MviViewEffectSender<Result : MviResult, ViewEffect : MviViewEffect> {
    suspend fun handleViewEffect(result: Result)
    fun getViewEffect(): SharedFlow<ViewEffect>
    val viewEffect : MutableSharedFlow<ViewEffect>
}

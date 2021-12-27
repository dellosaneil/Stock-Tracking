package com.example.compose.mvi.interfaces

import com.example.compose.test.TestViewEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface MviViewEffectSender<Result : MviResult, ViewEffect : MviViewEffect> {
    suspend fun handleViewEffect(result: Result)
    fun getViewEffect(): SharedFlow<ViewEffect>
    abstract val viewEffect : MutableSharedFlow<ViewEffect>
}

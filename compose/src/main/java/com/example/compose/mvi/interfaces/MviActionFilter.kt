package com.example.compose.mvi.interfaces

import kotlinx.coroutines.flow.Flow

interface MviActionFilter<I : MviIntent, A : MviAction> {
    fun intentFilter(intent: Flow<I>) : Flow<I>
    fun actionFromIntent(intent: Flow<I>): Flow<A>
}

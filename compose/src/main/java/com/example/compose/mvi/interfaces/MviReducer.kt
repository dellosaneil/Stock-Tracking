package com.example.compose.mvi.interfaces

import kotlinx.coroutines.flow.Flow

interface MviReducer<Result : MviResult, ViewState : MviViewState> {

    fun reduce(result: Flow<Result>): Flow<ViewState>
}

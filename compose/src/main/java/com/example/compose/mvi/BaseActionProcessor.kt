package com.example.compose.mvi

import com.example.compose.mvi.interfaces.MviAction
import com.example.compose.mvi.interfaces.MviResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class BaseActionProcessor<Action : MviAction, Result : MviResult> {

    abstract fun processAction(action: Flow<Action>): Flow<Result>

    fun toResult(resultSuccessBlock: suspend () -> Result): Flow<Result> {
        return flow {
            emit(resultSuccessBlock())
        }.flowOn(
            Dispatchers.IO
        )
    }

    fun toResult(
        resultSuccessBlock: suspend () -> Result,
        resultErrorBlock: (Throwable) -> Result
    ): Flow<Result> {
        return flow {
            emit(resultSuccessBlock.invoke())
        }.catch {
            emit(resultErrorBlock.invoke(it))
        }.flowOn(
            Dispatchers.IO
        )
    }

    fun toResult(
        resultSuccessBlock: suspend () -> Result,
        resultErrorBlock: (Throwable) -> Result,
        initialIntent: Result
    ): Flow<Result> {
        return flow {
            emit(resultSuccessBlock.invoke())
        }.catch {
            emit(resultErrorBlock.invoke(it))
        }.onStart {
            emit(initialIntent)
        }.flowOn(
            Dispatchers.IO
        )
    }

}

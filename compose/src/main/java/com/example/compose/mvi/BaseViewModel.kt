package com.example.compose.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.mvi.interfaces.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
abstract class BaseViewModel<Intent : MviIntent, Action : MviAction, ViewEffect : MviViewEffect, ViewState : MviViewState, Result : MviResult>(
    private val filter: MviActionFilter<Intent, Action>,
    private val viewEffectSender: MviViewEffectSender<Result, ViewEffect>,
    private val reducer: MviReducer<Result, ViewState>,
) :
    ViewModel() {

    private val initialState: ViewState by lazy { initialState() }
    abstract fun initialState(): ViewState
    val viewEffect: SharedFlow<ViewEffect> = viewEffectSender.getViewEffect()

    val currentState: ViewState
        get() = viewState.value

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(initialState)
    val viewState = _viewState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()

    fun emitIntent(intent: Intent) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    abstract fun actionProcessor(): BaseActionProcessor<Action, Result>

    init {
        subscribeToFlow()
    }

    private fun subscribeToFlow() {
        _intent
            .let(filter::intentFilter)
            .let(filter::actionFromIntent)
            .let(actionProcessor()::processAction)
            .onEach {
                viewEffectSender.handleViewEffect(it)
            }
            .let(reducer::reduce)
            .onEach { newState ->
                _viewState.value = newState
            }
            .launchIn(viewModelScope)
    }
}

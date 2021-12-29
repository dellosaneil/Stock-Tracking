package com.example.service.di

import com.example.service.usecase.WebSocketBuilder
import com.example.service.usecase.impl.WebSocketBuilderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindWebSocketBuilder(impl: WebSocketBuilderImpl): WebSocketBuilder
}

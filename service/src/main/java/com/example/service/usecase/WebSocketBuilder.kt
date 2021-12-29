package com.example.service.usecase

import com.tinder.scarlet.Scarlet

interface WebSocketBuilder {
    operator fun invoke(
        path : List<String>,
        query : List<String>,
    ) : Scarlet
}

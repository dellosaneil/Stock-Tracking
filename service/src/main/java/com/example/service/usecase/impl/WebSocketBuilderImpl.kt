package com.example.service.usecase.impl

import com.example.service.usecase.WebSocketBuilder
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient
import javax.inject.Inject

class WebSocketBuilderImpl @Inject constructor(
    private val webSocketBuilder: Scarlet.Builder,
    private val okHttpClient: OkHttpClient
) : WebSocketBuilder {
    companion object {
        private const val WEB_SOCKET_BASE_URL = "ws:192.168.1.9:8080"
    }

    override fun invoke(
        path: List<String>, query: List<String>,
    ): Scarlet {
        val urlPath = getPath(path)
        val queries = getQuery(query)
        return webSocketBuilder
            .webSocketFactory(okHttpClient.newWebSocketFactory("$WEB_SOCKET_BASE_URL$urlPath$queries"))
            .build()
    }

    private fun getPath(path: List<String>): String {
        val sb = StringBuilder()
        path.forEach {
            sb.append("/$it")
        }
        return sb.toString()
    }

    private fun getQuery(query: List<String>): String {
        val sb = StringBuilder()
        query.forEachIndexed { index, q ->
            if (index == 0) {
                sb.append("?$q")
            } else {
                sb.append("&$q")
            }
        }
        return sb.toString()
    }
}

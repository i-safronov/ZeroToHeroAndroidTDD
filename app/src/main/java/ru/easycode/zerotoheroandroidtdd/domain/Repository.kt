package ru.easycode.zerotoheroandroidtdd.domain

import ru.easycode.zerotoheroandroidtdd.data.model.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.data.service.retrofit.SimpleService

interface Repository {

    suspend fun load(): SimpleResponse

    class Base(
        private val service: SimpleService,
        private val url: String
    ): Repository {

        override suspend fun load(): SimpleResponse {
            return service.fetch(url = url)
        }

    }

}

package ru.easycode.zerotoheroandroidtdd.domain

import ru.easycode.zerotoheroandroidtdd.data.LoadResult
import ru.easycode.zerotoheroandroidtdd.data.model.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.data.service.retrofit.SimpleService
import java.net.UnknownHostException

interface Repository {

    suspend fun load(): LoadResult

    class Base(
        private val service: SimpleService,
        private val url: String
    ): Repository {

        override suspend fun load(): LoadResult {
            return try {
                LoadResult.Success(service.fetch(url = url))
            } catch (e: Exception) {
                LoadResult.Error(e is UnknownHostException)
            }
        }

    }

}

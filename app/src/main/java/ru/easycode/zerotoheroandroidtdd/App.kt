package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.easycode.zerotoheroandroidtdd.data.service.retrofit.SimpleService
import ru.easycode.zerotoheroandroidtdd.domain.Repository

class App : Application() {

    private lateinit var viewModel: MainViewModel
    private lateinit var service: SimpleService

    override fun onCreate() {
        super.onCreate()
        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply { addInterceptor(intercepter) }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        viewModel = MainViewModel(
            repository = Repository.Base(
                service = retrofit.create(SimpleService::class.java),
                url = URL
            )
        )
    }

    fun vm() = viewModel

    companion object {
        private const val URL =
            "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }

}
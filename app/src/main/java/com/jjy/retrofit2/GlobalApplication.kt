package com.jjy.retrofit2

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GlobalApplication : Application() {
    companion object {
        const val BASE_URL: String = "https://ceo.baemin.com/cms/v1/"
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
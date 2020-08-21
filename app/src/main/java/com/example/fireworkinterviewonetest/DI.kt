package com.example.fireworkinterviewonetest

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://hashtag-experiment.sandbox.fireworktv.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()

        retrofit.create(RelatedItemsService::class.java)
    }

    viewModel { MainActivityViewModel(get()) }

}
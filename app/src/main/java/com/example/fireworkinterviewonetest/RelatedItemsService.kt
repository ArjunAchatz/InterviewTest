package com.example.fireworkinterviewonetest

import retrofit2.http.GET
import retrofit2.http.Query


interface RelatedItemsService {

    @GET("api/hashtags/_relationships")
    suspend fun getRelatedItemsResponse(@Query("hashtag") hashTag: String): Map<String, Int>

}


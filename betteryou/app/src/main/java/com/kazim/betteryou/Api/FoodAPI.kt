package com.kazim.betteryou.Api

import com.kazim.betteryou.Data.Chicken
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodAPI {

    //make a get request
    @GET("filter.php?")
    suspend fun getFeedList(@Query("i") categoryName:String): Response<Chicken>
}
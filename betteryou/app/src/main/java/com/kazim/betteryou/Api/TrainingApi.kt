package com.kazim.betteryou.Api

import com.kazim.betteryou.Data.SportResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TrainingApi {

    //make a get request
    @GET("exercises?")
    suspend fun getType(@Query("rapidapi-key") api:String,@Query("type") type:String):Response<List<SportResponse>>

    //make a get request
    @GET("exercises?")
    suspend fun getCardio(@Query("rapidapi-key") api:String,@Query("type") type:String,@Query("difficulty") difficulty:String):Response<List<SportResponse>>

    //make a get request
    @GET("exercises?")
    suspend fun getMuscle(@Query("rapidapi-key") api:String ,@Query("muscle") muscle:String):Response<List<SportResponse>>
}
package com.kazim.betteryou.Repository

import com.kazim.betteryou.Data.Chicken
import com.kazim.betteryou.Data.SportResponse
import retrofit2.Response

interface SportRepositoryInterface {
    suspend fun getType(type:String):Resource<List<SportResponse>>
    suspend fun getMuscle(muscle:String): Resource<List<SportResponse>>
    suspend fun getCardio(type:String,difficulty:String): Resource<List<SportResponse>>
    suspend fun getFoods(i:String):Resource<Chicken>
}
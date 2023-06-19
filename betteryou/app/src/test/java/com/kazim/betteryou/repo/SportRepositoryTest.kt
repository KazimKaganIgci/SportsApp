package com.kazim.betteryou.repo

import com.kazim.betteryou.Data.Chicken
import com.kazim.betteryou.Data.SportResponse
import com.kazim.betteryou.Repository.Resource
import com.kazim.betteryou.Repository.SportRepositoryInterface

class SportRepositoryTest:SportRepositoryInterface {
    override suspend fun getType(type: String): Resource<List<SportResponse>> {
        return Resource.success(listOf())
    }

    override suspend fun getMuscle(muscle: String): Resource<List<SportResponse>> {
        return Resource.success(listOf())
    }

    override suspend fun getCardio(
        type: String,
        difficulty: String
    ): Resource<List<SportResponse>> {
        return Resource.success(listOf())
    }

    override suspend fun getFoods(i: String): Resource<Chicken> {
        return Resource.success(Chicken(listOf()))
    }
}
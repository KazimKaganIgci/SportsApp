package com.kazim.betteryou.Repository

import com.kazim.betteryou.Api.FoodAPI
import com.kazim.betteryou.Api.TrainingApi
import com.kazim.betteryou.Data.Chicken
import com.kazim.betteryou.Data.SportResponse
import com.kazim.betteryou.Module.Constant.API_KEY
import retrofit2.Response
import javax.inject.Inject



class SportRepository  @Inject constructor(private val trainingApi: TrainingApi,private val foodAPI: FoodAPI):SportRepositoryInterface {


    override suspend fun getType(type:String): Resource<List<SportResponse>> {
        return try{
            val response =trainingApi.getType(API_KEY,type)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Error",null)

            }else{
                Resource.error("Error",null)
            }
        }catch (e:Exception){
            Resource.error("No data",null)
        }
    }

    override suspend fun getMuscle(muscle:String): Resource<List<SportResponse>> {
        return try{
            val response = trainingApi.getMuscle(API_KEY,muscle)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Error",null)

            }else{
                Resource.error("Error",null)
            }
        }catch (e:Exception){
            Resource.error("No data",null)
        }
    }
    override suspend fun getCardio(type:String, difficulty:String): Resource<List<SportResponse>> {

        return try{
            val response=trainingApi.getCardio(API_KEY,type,difficulty)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Error",null)

            }else{
                Resource.error("Error",null)
            }
        }catch (e:Exception){
            Resource.error("No data",null)
        }

    }

    override suspend fun getFoods(i:String): Resource<Chicken> {
        return try{
            val response =foodAPI.getFeedList(i);
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Error",null)

            }else{
                Resource.error("Error",null)
            }
        }catch (e:Exception){
            Resource.error("No data",null)
        }

    }


}
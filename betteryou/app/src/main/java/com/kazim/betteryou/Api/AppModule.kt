package com.kazim.betteryou.Api


import com.kazim.betteryou.Module.Constant.BASE_KEY
import com.kazim.betteryou.Repository.SportRepository
import com.kazim.betteryou.Repository.SportRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //Automatically create 1 object
    @Provides
    @Singleton
    fun sportsApi():TrainingApi= Retrofit.Builder()
        .baseUrl(BASE_KEY)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TrainingApi::class.java)

    //Automatically create 1 object
    @Provides
    @Singleton
    fun provideApi():FoodAPI=Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodAPI::class.java)

    //Automatically create 1 object
    @Singleton
    @Provides
    fun injectNormalRepo(api:TrainingApi, api1:FoodAPI) = SportRepository(api,api1) as SportRepositoryInterface


}
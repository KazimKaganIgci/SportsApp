package com.kazim.betteryou.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazim.betteryou.Data.Chicken
import com.kazim.betteryou.Data.Meal
import com.kazim.betteryou.Data.SportResponse
import com.kazim.betteryou.Repository.Resource
import com.kazim.betteryou.Repository.SportRepository
import com.kazim.betteryou.Repository.SportRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val sportRepository:SportRepositoryInterface):
    ViewModel(){


    //live data retrieval
    private val _getTypeLiveData =MutableLiveData<Resource<List<SportResponse>>>()
    val getTypeLiveData: LiveData<Resource<List<SportResponse>>> =_getTypeLiveData

    fun getTypeList(type:String){
        viewModelScope.launch {
            val response =sportRepository.getType(type)
                    _getTypeLiveData.value = response }


    }

    //live data retrieval
    private val _getMuscleLiveData =MutableLiveData<Resource<List<SportResponse>>>()
    val getMuscleLiveData: LiveData<Resource<List<SportResponse>>> =_getMuscleLiveData

    fun getMuscleList(muscle:String){
        viewModelScope.launch {
            val response =sportRepository.getMuscle(muscle)

                    _getMuscleLiveData.value = response
        }
        }




    //live data retrieval
    private val _getCardioLiveData =MutableLiveData<Resource<List<SportResponse>>>()
    val getCardioLiveData: LiveData<Resource<List<SportResponse>>> =_getCardioLiveData

    fun getCardioList(type:String,difficulty:String){
        viewModelScope.launch {
            val response =sportRepository.getCardio(type,difficulty)

                _getCardioLiveData.value = response

        }
    }



    //live data retrieval
    private val _chickenList= MutableLiveData<Resource<Chicken>>()
    val chickenMealLivedata: LiveData<Resource<Chicken>> =_chickenList
    fun getChickenList(){

        viewModelScope.launch {
            val response =sportRepository.getFoods("chicken_breast")
                    _chickenList.value =response



        }
    }

}




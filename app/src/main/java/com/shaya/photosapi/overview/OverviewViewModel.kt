package com.shaya.photosapi.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaya.photosapi.network.Photo
import com.shaya.photosapi.network.PhotoApi
import kotlinx.coroutines.launch


enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel: ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _photos = MutableLiveData<List<Photo>>()
    val photo: LiveData<List<Photo>> = _photos

    init {
        getPhotos()
    }

    private fun getPhotos(){
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _photos.value = PhotoApi.retrofitService.getPhotos()
                _status.value = ApiStatus.DONE

            }catch (e: Exception){
                _status.value = ApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }



}
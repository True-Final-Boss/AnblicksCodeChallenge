package com.example.anblickscodechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anblickscodechallenge.model.VMData
import com.example.anblickscodechallenge.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RadioViewModel(
    val repository: Repository
) : ViewModel() {

    private val _channelLiveData = MutableLiveData<VMData>()
    val channelLiveData: LiveData<VMData>
        get() = _channelLiveData


    fun getChannelResponse() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = repository.getChannels()
                _channelLiveData.postValue(VMData.Response(response))
            } catch(e: Exception){
                _channelLiveData.postValue(VMData.Error(e.message?:e.localizedMessage))
            }
        }
    }
}
package com.example.anblickscodechallenge.model.repository

import com.example.anblickscodechallenge.model.ChannelResponse
import com.example.anblickscodechallenge.network.ChannelApiInterface
import com.example.anblickscodechallenge.network.RetrofitClient
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor() {
    suspend fun getChannels(): ChannelResponse {
        return RetrofitClient.getService().getChannelResponse()
    }
}

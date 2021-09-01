package com.example.anblickscodechallenge.network

import com.example.anblickscodechallenge.model.ChannelResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ChannelApiInterface {
    @GET("jvanaria/jvanaria.github.io/master/channels.json")
    suspend fun getChannelResponse(): ChannelResponse
}